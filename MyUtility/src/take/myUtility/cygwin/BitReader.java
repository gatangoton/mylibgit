package take.myUtility.cygwin;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Streamのpos byte目からlen bit/wordとして、n wordを読む。
 * 読み込みの際に一度にbufferに読み込むので相応のメモリを消費する。<br>
 *
 * 1 <= len <= 15		1 wordは15bitまで。short(16bit)で最上位bitは符号のため。最上位bitを含め不要なbitは0で埋める。
 * 0 <= pos 			posはファイルの先頭を0 byte目とした読み込み開始の位置。
 * 1 <= n				読み込むwordの数。読み込む量は、len * n bit ≒ (len * n) / 8 byte。
 *
 * @author taketo
 *
 */
public class BitReader {
	private String fn;
	private long startPos;
	private int wordLength;
	private long numOfWord;

	public BitReader(String fileName, long pos, int wl, long n) throws Exception{
		fn = fileName;
		startPos = pos;
		wordLength = wl;
		numOfWord = n;

		if(startPos < 0){
			throw new Exception("position out of range");
		}
		if((wordLength <= 0) || (wordLength > 15)){
			throw new Exception("word length out of range");
		}
		if(numOfWord <= 0){
			throw new Exception("number of word out of range");
		}

		/*
		for (int i=0; i <= 20; i++){
		System.out.println(i + ":" +rawData[i]);
		}*/
	}


	public short [] readWords() throws FileNotFoundException, IOException{
		byte[] rawData;
		RandomAccessFile raf;
		int rawBuff = 0, rawBit = 0;	//読み込んだもの
		int wBuff1, wBit;				//wordに入れるもの
		short[] retData = new short[(int)numOfWord];

		rawData = new byte[((wordLength * numOfWord % 8) == 0) ?
				(int)(wordLength * numOfWord / 8)
				:(int)(wordLength * numOfWord / 8 + 1)];
		raf = new RandomAccessFile(fn, "r");
		raf.seek(startPos);
		raf.read(rawData);
		raf.close();
		ByteArrayInputStream bais = new ByteArrayInputStream(rawData);

		for(int i = 0; i < numOfWord; i++){
			wBuff1 = 0;
			wBit = wordLength;
			do{
				if(rawBit <= 0){
					rawBuff = bais.read();	//このメソッドはunsigned byteとした値、0-255が返る。
					rawBit = 8;
				}

				wBuff1 = wBuff1 << ((wBit >= rawBit) ? rawBit : wBit);
				if(wBit >= rawBit){
					wBuff1 = wBuff1 | lowerBit(rawBuff, rawBit);
					wBit -= rawBit;
					rawBit = 0;
				}else{
					wBuff1 = wBuff1 | upperBit(rawBuff, wBit);
					rawBit -= wBit;
					wBit = 0;
				}
			}while(wBit > 0);
			retData[i] = (short)wBuff1;
		}




		return retData;
	}

	/**
	 * dataの下位bitのみを残す。
	 * @param i 残す下位bit。
	 * @return
	 */
	public int lowerBit(int data, int bit){
		return data & (0xFFFFFFFF >>> (32 - bit));
	}

	/**
	 * 8bitデータのdataの上位bitのみを残す。<br>
	 * 例<br>
	 * 0000 0000 1011 0011 元のデータ<br>
	 * 0000 0000 1110 0000 マスク用（上位3bitをとる）<br>
	 * 0000 0000 1010 0000 マスクされたデータ<br>
	 * 0000 0000 0000 0101 下位へ移動
	 * @param i 残す上位bit。
	 * @return
	 */
	public int upperBit(int data, int bit){
		return (data & ((0x000000FF << (8 - bit)) & 0x000000FF))
					>>> (8 - bit);
	}

}

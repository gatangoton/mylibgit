package take;

import java.io.FileNotFoundException;

import take.myUtility.cygwin.*;

public class Practice3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		String fileName = "C:\\cygwin\\home\\taketo\\temp\\test_grib2.bin";
		BitReader b = null;
		short[] val = null;

		try {
			b = new BitReader(fileName, 12L, 11, 250000);
			val = b.readWords();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		for(int i = 0; i < 100; i++){
			System.out.println(i + ":" +val[i]+"\t"+ String.format("%03X", +val[i]));
		}
	}

	private static long connect4(byte d[]){
		long a0, a1, a2, a3;

		if(d.length != 4){
			return 0;
		}
		a0 = (d[0] & 0xff);
		a1 = (d[1] & 0xff) << 8;
		a2 = (d[2] & 0xff) << 16;
		a3 = ((long)(d[3] & 0xff)) << 24;

		return a0 + a1 + a2 + a3;
	}

}

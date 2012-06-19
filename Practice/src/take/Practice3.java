package take;

import take.myUtility.cygwin.Grib2Reader;
import take.myUtility.cygwin.ReadBin;

public class Practice3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//new Grib2Reader();

		//System.out.println("Practice3");

		//byte[] d = {(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff};
		//System.out.println(String.format("%1$d",connect4(d)));
		
		byte b = -1;
		int i = 0x3F000000;
		System.out.println(String.format("%#010x",i));
		float f = 0xFFFFFFFF;
		System.out.println(Float.intBitsToFloat(i));
		
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

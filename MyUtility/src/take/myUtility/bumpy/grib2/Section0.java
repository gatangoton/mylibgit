package take.myUtility.bumpy.grib2;


public class Section0 extends Section {

	public Section0(byte[] b, long pos) throws Exception{
		super(b, pos);

		if((b.length != 16)||
			(b[0] != 0x47)||		//'G'
			(b[1] != 0x52)||		//'R'
			(b[2] != 0x49)||		//'I'
			(b[3] != 0x42)||		//'B'
			(b[6] != 0) ||		//Metrological products
			(b[7] != 2) ||		//currently must be 2
			(Long.signum(totalLength()) != 1) //大きすぎてlongで表現できない場合を回避
			){
			throw new Exception("unable to parse section 0 data");
		}
	}

	/**
	 * GRIB2全体の長さを求める。(9-16 Octet)
	 * @return
	 */
	public long totalLength(){
		byte[] l = new byte[8];
		byte[] d = getData();
		for(int i = 0; i <= 7; i++){
			l[i] = d[i+8];
		}
		return connect8(l);
	}

	@Override
	public long sectionLength(){
		return 16L;
	}

	@Override
	public String toString() {
		String retVal = "----section 0----\n" +
			"1-4:GRIB\t\t'GRIB'\n" +
			"5-6:" + String.format("%#06X", getOctetValue(5, 6)) + "\t\tReserved\n" +
			"7:" + getOctetValue(7) + "\t\tDiscipline = Meteorological products\n" +
			"9-16:" + totalLength() + "\t\tTotal Length of GRIB message in octets\n";

		return retVal;
	}
}

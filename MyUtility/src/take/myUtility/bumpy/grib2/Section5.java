package take.myUtility.bumpy.grib2;


public class Section5 extends Section {
	public Section5(byte b[], long pos) throws Exception{
		super(b, pos);

		if(	(getOctetValue(5) != 5) ||		//section 5
			(codeTable50(getOctetValue(10, 11)).equals("not supported"))  //Template no
				//(getOctetValue(11) != 0) ||	//Number of octets for optional list of numbers defining number of points
				//(getOctetValue(12) != 0) ||	//Interpretation of list of numbers at end of section 3
				//(codeTable31(getOctetValue(13, 14)).equals("not supported")) 	//Grid definition Template no
				){
				throw new Exception("unable to parse section 5 data");
			}
	}

	@Override
	public String toString() {
		long l1;
		String retVal = "----section 5----\n" +
		"1-4:" + sectionLength() + "\t\tSection Length\n" +
		"5:" + sectionNo() + "\t\tSection no\n" +
		"6-9:" + getOctetValue(6, 9) + "\t\tNumber of data points where one or more values are specified in Section 7 when a bit map is present, total number of data points when a bit map is absent\n" +
		"10-11:" + (l1 =getOctetValue(10, 11)) + "\t\tData Representation Template Number = " + codeTable50(l1) + "\n" ;

		if(l1 == 0){
			retVal += template50();
		}
		return retVal;
	}

	private String template50(){
		long l1;

		String retVal =  "\t----template 5.0----\n" +
		"12-15:" + Float.intBitsToFloat((int)(getOctetValue(12, 15) & 0x00000000FFFFFFFFL)) + "\t\tReference value (R) (IEEE 32-bit floating-point value)\n" +
		"16-17:" + getOctetValueNegative(16, 17) + "\t\tBinary scale factor (E)\n" +
		"18-19:" + getOctetValueNegative(18, 19) + "\t\tDecimal scale factor (D)\n" +
		"20:" + getOctetValue(20) + "\t\tNumber of bits used for each packed value for simple packing, or for each group reference value for complex packing or spatial differencing\n" +
		"21:" + (l1 =getOctetValue(21)) + "\t\tType of original field values = " + codeTable51(l1) + "\n";

		return retVal;
	}

	private String codeTable50(long i){
		String retVal = "not supported";
		switch((int)i){
			case 0: retVal = "Grid point data - simple packing";
					break;
		}
		return retVal;
	}

	private String codeTable51(long i){
		String retVal = "not supported";
		switch((int)i){
			case 0: retVal = "Floating point";
					break;
		}
		return retVal;
	}

}

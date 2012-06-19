package take.myUtility.cygwin;

public class Section1 extends Section {

	public Section1(byte[] b) throws Exception{
		super(b);

		if(	(b[4] != 1) ||		//section 1
			(codeTableC1(getOctetValue(6,7)).equals("not supported")) || //Tokyo
			(codeTable10(getOctetValue(10)).equals("not supported")) ||	//Grib Master Table Version no
			(codeTable11(getOctetValue(11)).equals("not supported")) ||	//Grib Local Table Version no
			(codeTable12(getOctetValue(12)).equals("not supported")) ||	//Reference Time
			(codeTable13(getOctetValue(20)).equals("not supported")) ||	//Production status
			(codeTable14(getOctetValue(21)).equals("not supported"))){	//Type of data
			throw new Exception("unable to parse section 1 data");
		}
	}

	@Override
	public String toString() {
		long l1,l2,l3,l4,l5,l6;
		String retVal = "----section 1----\n" +
		"1-4:" + sectionLength() + "\t\tSection Length\n" +
		"5:" + sectionNo() + "\t\tSection no\n" +
		"6-7:" + (l1 = getOctetValue(6, 7)) + "\t\tID of center = " + codeTableC1(l1) + "\n" +
		"8-9:" + getOctetValue(8, 9) + "\t\tID of sub center\n" +
		"10:" + (l2 = getOctetValue(10)) + "\t\tMaster Tables Version no = " + codeTable10(l2) + "\n" +
		"11:" + (l3 = getOctetValue(11)) + "\t\tLocal Tables Version no = " + codeTable11(l3) + "\n" +
		"12:" + (l4 = getOctetValue(12)) + "\t\tReference Time = " + codeTable12(l4) + "\n" +
		"13-14:" + getOctetValue(13, 14) + "\t\tYear\n" +
		"15:" + getOctetValue(15) + "\t\tMonth\n" +
		"16:" + getOctetValue(16) + "\t\tDay\n" +
		"17:" + getOctetValue(17) + "\t\tHour\n" +
		"18:" + getOctetValue(18) + "\t\tMinute\n" +
		"19:" + getOctetValue(19) + "\t\tSecond\n" +
		"20:" + (l5 = getOctetValue(20)) + "\t\tProduction status = " + codeTable13(l5) + "\n" +
		"21:" + (l6 = getOctetValue(21)) + "\t\tType of processed data = " + codeTable14(l6) + "\n";

		return retVal;
	}

	private String codeTableC1(long i){
		String retVal = "not supported";
		switch((int)i){
			case 34: retVal = "Tokyo";
		}
		return retVal;
	}

	private String codeTable10(long i){
		String retVal = "not supported";
		switch((int)i){
			case 2: retVal = "Version implemented on 4 NOV 2003";
		}
		return retVal;
	}

	private String codeTable11(long i){
		String retVal = "not supported";
		switch((int)i){
			case 1: retVal = "local table version 1";
		}
		return retVal;
	}

	private String codeTable12(long i){
		String retVal = "not supported";
		switch((int)i){
			case 1: retVal = "start of forecast";
		}
		return retVal;
	}

	private String codeTable13(long i){
		String retVal = "not supported";
		switch((int)i){
			case 0: retVal = "Operational products";
		}
		return retVal;
	}

	private String codeTable14(long i){
		String retVal = "not supported";
		switch((int)i){
			case 1: retVal = "Forecast products";
		}
		return retVal;
	}
}

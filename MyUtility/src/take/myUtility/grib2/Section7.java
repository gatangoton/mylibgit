package take.myUtility.grib2;


public class Section7 extends Section {

	public Section7(byte b[], long pos) throws Exception {
		super(b, pos);

		if ((getOctetValue(5) != 7) // section 7
		) {
			throw new Exception("unable to parse section 7 data");
		}
	}

	@Override
	public String toString() {
		int t;
		String retVal = "----section 7----\n" +
				"1-4:" + sectionLength() + "\t\tSection Length\n" +
				"5:" + sectionNo() + "\t\tSection no\n" +
				"6-" + sectionLength() + ":" + "\t\tData\n";
		/*
		for(int i = 5; i <= 364363; i++){
			t = getOctetValue(i) ;
			if(i == 364363){
				System.out.println();
			}
			if((t & 0xff) != 0xff){
			retVal +=  i +":" + t + "\n";
			}
		}*/
		return retVal;
	}


}

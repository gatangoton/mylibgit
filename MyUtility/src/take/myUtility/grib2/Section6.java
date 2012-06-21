package take.myUtility.grib2;


public class Section6 extends Section {

	public Section6(byte b[], long pos) throws Exception {
		super(b, pos);

		if ((getOctetValue(5) != 6) // section 6
		) {
			throw new Exception("unable to parse section 6 data");
		}
	}

	@Override
	public String toString() {
		String retVal = "----section 6----\n" +
				"1-4:" + sectionLength() + "\t\tSection Length\n" +
				"5:" + sectionNo() + "\t\tSection no\n" +
				"6:" + String.format("%#04X", getOctetValue(6)) + "\n";
		return retVal;
	}

}

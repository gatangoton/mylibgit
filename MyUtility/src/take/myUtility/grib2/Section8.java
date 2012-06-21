package take.myUtility.grib2;

import take.myUtility.cygwin.Section;

public class Section8 extends Section {

	public Section8(byte b[], long pos) throws Exception {
		super(b, pos);
		if ((getOctetValue(1) != 0x37) ||	// '7'
			(getOctetValue(2) != 0x37) ||	// '7'
			(getOctetValue(3) != 0x37) ||	// '7'
			(getOctetValue(4) != 0x37)){	// '7'
			throw new Exception("unable to parse section 8 data");
		}
	}

	@Override
	public String toString() {
		String retVal = "----section 8----\n" +
			"1:" + String.format("%X", getOctetValue(1)) + "\t\t'7'\n" +
			"2:" + String.format("%X", getOctetValue(2)) + "\t\t'7'\n" +
			"3:" + String.format("%X", getOctetValue(3)) + "\t\t'7'\n" +
			"4:" + String.format("%X", getOctetValue(4)) + "\t\t'7'\n";
		return retVal;
	}

}

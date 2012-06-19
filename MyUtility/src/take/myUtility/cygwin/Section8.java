package take.myUtility.cygwin;

public class Section8 extends Section {

	public Section8(byte b[]) throws Exception {
		super(b);
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
			"1:" + String.format("%X", getOctetValue(1)) + "\n" +
			"2:" + String.format("%X", getOctetValue(2)) + "\n" +
			"3:" + String.format("%X", getOctetValue(3)) + "\n" +
			"4:" + String.format("%X", getOctetValue(4)) + "\n";
		return retVal;
	}

}

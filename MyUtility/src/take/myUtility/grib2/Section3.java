package take.myUtility.grib2;


public class Section3 extends Section {

	public Section3(byte[] b, long pos) throws Exception{
		super(b, pos);

		if(	(getOctetValue(5) != 3) ||		//section 3
			(codeTable30(getOctetValue(6)).equals("not supported")) ||  //Source of grid definition
			(getOctetValue(11) != 0) ||	//Number of octets for optional list of numbers defining number of points
			(getOctetValue(12) != 0) ||	//Interpretation of list of numbers at end of section 3
			(codeTable31(getOctetValue(13, 14)).equals("not supported")) 	//Grid definition Template no
			){
			throw new Exception("unable to parse section 3 data");
		}

		if(getOctetValue(13, 14) == 0){		//Template 3.0
			if(codeTable32(getOctetValue(15)).equals("not supported") ||
				((byte)getOctetValue(16) != (byte)0xff) ||
				(getOctetValue(17, 20) !=  0xffffffffL) ||
				((byte)getOctetValue(21) != (byte)0xff) ||
				(getOctetValue(22, 25) !=  0xffffffffL) ||
				((byte)getOctetValue(26) != (byte)0xff) ||
				(getOctetValue(27, 30) !=  0xffffffffL)


					){
				throw new Exception("unable to parse section 3 data");
			}
		}

	}

	@Override
	public String toString() {
		long l1,l2,l3;
		String retVal = "----section 3----\n" +
		"1-4:" + sectionLength() + "\t\tSection Length\n" +
		"5:" + sectionNo() + "\t\tSection no\n" +
		"6:" + (l1 = getOctetValue(6)) + "\t\tSource of grid definition = " + codeTable30(l1) + "\n" +
		"7-10:" + getOctetValue(7, 10) + "\t\tNumber of data points\n" +
		"11:" + getOctetValue(11) + "\t\tNumber of octets for optional list of numbers defining number of points\n" +
		"12:" + (l2 = getOctetValue(12)) + "\t\tInterpretation of list of numbers at end of section 3 = " + codeTable311(l2) + "\n" +
		"13-14:" + (l3 = getOctetValue(13, 14)) + "\t\tGrid definition Template no = " + codeTable31(l3) + "\n";

		if(l3 == 0){
			retVal += template30();
		}
		return retVal;
	}

	private String template30(){
		long l1,l2,l3;

		String retVal = "\t----template 3.0----\n" +
		"15:" + (l1 = getOctetValue(15)) + "\t\tShape of the Earth = " + codeTable32(l1) + "\n" +
		"16:" + String.format("%#04X", (byte)getOctetValue(16)) + "\n" +
		"17-20:" + String.format("%#010X", getOctetValue(17, 20)) + "\n" +
		"21:" + String.format("%#04X", (byte)getOctetValue(21)) + "\n" +
		"22-25:" + String.format("%#010X", getOctetValue(22, 25)) + "\n" +
		"26:" + String.format("%#04X", (byte)getOctetValue(26)) + "\n" +
		"27-30:" + String.format("%#010X", getOctetValue(27, 30)) + "\n" +
		"31-34:" + getOctetValue(31, 34) + "\t\tnumber of points along a parallel\n" +
		"35-38:" + getOctetValue(35, 38) + "\t\tnumber of points along a meridian\n" +
		"39-42:" + getOctetValue(39, 42) + "\t\tBasic angle of the initial production domain\n" +
		"43-46:" + String.format("%#010X", getOctetValue(43, 46)) + "\tSubdivisions of basic angle used to define extreme longitudes and latitudes, and direction increments\n" +
		"47-50:" + getOctetValue(47, 50) + "\tLa1 - latitude of first grid point\n" +
		"51-54:" + getOctetValue(51, 54) + "\tLo1 - longitude of first grid point\n" +
		"55:" + (l2 = getOctetValue(55)) + "\t\tResolution and component flags = " + flagTable33(l2) + "\n" +
		"56-59:" + getOctetValue(56, 59) + "\tLa2 - latitude of last grid point\n" +
		"60-63:" + getOctetValue(60, 63) + "\tLo2 - longitude of last grid point\n" +
		"64-67:" + getOctetValue(64, 67) + "\t\tDi - i direction increment\n" +
		"68-71:" + getOctetValue(68, 71) + "\t\tDj - j direction increment\n" +
		"72:" + (l3 = getOctetValue(72)) + "\t\tScanning mode = " + flagTable34(l3) + "\n";

		;
		;

		return retVal;
	}

	private String codeTable30(long i){
		String retVal = "not supported";
		switch((int)i){
			case 0: retVal = "Specified in Code Table 3.1 (Octet 13-14)";
		}
		return retVal;
	}

	private String codeTable31(long i){
		String retVal = "not supported";
		switch((int)i){
			case 0: retVal = "Latitude/Longitude";
		}
		return retVal;
	}

	private String codeTable32(long i){
		String retVal = "not supported";
		switch((int)i){
			case 6: retVal = "Earth assumed spherical with radius of 6 371 229.0 m";
		}
		return retVal;
	}

	private String codeTable311(long i){
		String retVal = "not supported";
		switch((int)i){
			case 0: retVal = "There is no appended list";
		}
		return retVal;
	}

	private String flagTable33(long i){
		//String retVal = "not supported";
		String bit3 = "", bit4 = "", bit5 = "";

		if((i & 0x20L) == 0){
			bit3 = "i direction increments not given";
		}else{
			bit3 = "i direction increments given";
		}
		if((i & 0x10L) == 0){
			bit4 = "j direction increments not given";
		}else{
			bit4 = "j direction increments given";
		}
		if((i & 0x08L) == 0){
			bit5 = "Resolved u- and v- components of vector quantities relative to easterly and northerly directions";
		}else{
			bit5 = "Resolved u- and v- components of vector quantities relative to the defined grid in the direction of increasing x and y (or i and j) coordinates respectively";
		}

		return bit3 + "\n\t\t\t\t" + bit4 + "\n\t\t\t\t" + bit5;
		/*
		Flag Table 3.3: Resolution and Component Flags

		Number	Value	Meaning
		1-2				Reserved
		3		0 		i direction increments not given
		  		1		i direction increments given
		4		0		j direction increments not given
				1		j direction increments given
		5		0		Resolved u- and v- components of vector quantities relative to easterly and northerly directions
				1		Resolved u- and v- components of vector quantities relative to the defined grid in the direction of increasing x and y (or i and j) coordinates respectively
		6-8				Reserved - set to zero

		(Bit Number 12345678)
		*/
	}

	private String flagTable34(long i){
		//String retVal = "not supported";
		String bit1 = "", bit2 = "", bit3 = "", bit4 = "";

		if((i & 0x80L) == 0){
			bit1 = "Points of first row or column scan in the +i (+x) direction";
		}else{
			bit1 = "Points of first row or column scan in the -i (-x) direction";
		}
		if((i & 0x40L) == 0){
			bit2 = "Points of first row or column scan in the -j (-y) direction";
		}else{
			bit2 = "Points of first row or column scan in the +j (+y) direction";
		}
		if((i & 0x20L) == 0){
			bit3 = "Adjacent points in i (x) direction are consecutive";
		}else{
			bit3 = "Adjacent points in j (y) direction is consecutive";
		}
		if((i & 0x10L) == 0){
			bit4 = "All rows scan in the same direction";
		}else{
			bit4 = "Adjacent rows scan in opposite directions";
		}

		return bit1 + "\n\t\t\t" + bit2 + "\n\t\t\t" + bit3 + "\n\t\t\t" + bit4;
		/*
		Flag Table 3.4: Scanning Mode
		Bit Number	Value	Meaning
		1			0		Points of first row or column scan in the +i (+x) direction
					1		Points of first row or column scan in the -i (-x) direction
		2			0		Points of first row or column scan in the -j (-y) direction
					1		Points of first row or column scan in the +j (+y) direction
		3			0		Adjacent points in i (x) direction are consecutive
					1		Adjacent points in j (y) direction is consecutive
		4			0		All rows scan in the same direction
					1		Adjacent rows scan in opposite directions
		5-8					Reserved
			Notes:
			(1) i direction: west to east along a parallel or left to right along an X-axis
			(2) j direction: south to north along a meridian, or bottom to top along a Y-axis
			(3) If bit number 4 is set, the first row scan is as defined by previous flags

		(Bit Number 12345678)
		*/
	}
}

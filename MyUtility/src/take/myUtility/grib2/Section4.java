package take.myUtility.grib2;


public class Section4 extends Section{

	public Section4(byte b[], long pos) throws Exception{
		super(b, pos);

		if(	(getOctetValue(5) != 4) ||		//section 4
			(getOctetValue(6, 7) != 0) ||
			(codeTable40(getOctetValue(8, 9)).equals("not supported"))  //Product Definition Template Number
				//(getOctetValue(11) != 0) ||	//Number of octets for optional list of numbers defining number of points
				//(getOctetValue(12) != 0) ||	//Interpretation of list of numbers at end of section 3
				//(codeTable31(getOctetValue(13, 14)).equals("not supported")) 	//Grid definition Template no
				){
				throw new Exception("unable to parse section 4 data");
			}
	}

	@Override
	public String toString() {
		long l1;
		String retVal = "----section 4----\n" +
		"1-4:" + sectionLength() + "\t\tSection Length\n" +
		"5:" + sectionNo() + "\t\tSection no\n" +
		"6-7:" + getOctetValue(6, 7) + "\t\tNumber of coordinate values after Template\n" +
		"8-9:" + (l1 =getOctetValue(8, 9)) + "\t\tProduct Definition Template Number = " + codeTable40(l1) + "\n" ;

		if(l1 == 0L){
			retVal += template40();
		}
		if(l1 == 8L){
			retVal += template48();
		}
		return retVal;
	}

	private String template4048(){
		long l1,l2,l3,l4,l5,l6;

		String retVal =
		"10:" + (l1 = getOctetValue(10)) + "\t\tParameter category = " + codeTable41(l1) + "\n" +
		"11:" + (l2 = getOctetValue(11)) + "\t\tParameter no = " + codeTable42(l2, l1) + "\n" +
		"12:" + (l3 = getOctetValue(12)) + "\t\tType of generating process = " + codeTable43(l3) + "\n" +
		"13:" + String.format("%#04X", (byte)getOctetValue(13)) + "\t\tBackground generating process identifier (defined by originating centre)\n" +
		"14:" + String.format("%#04X", (byte)getOctetValue(14)) + "\t\tAnalysis or forecast generating process identifier (defined by originating centre)\n" +
		"15-16:" + getOctetValue(15, 16) + "\t\tHours of observational data cut-off after reference time\n" +
		"17:" + getOctetValue(17) + "\t\tMinutes of observational data cut-off after reference time\n" +
		"18:" + (l4 = getOctetValue(18)) + "\t\tIndicator of unit of time range = " + codeTable44(l4) + "\n" +
		"19-22:" + getOctetValue(19, 22) + "\t\tForecast time in units defined by octet 18\n" +
		"23:" + (l5 = getOctetValue(23)) + "\t\tType of first fixed surface = " + codeTable45(l5) + "\n" +
		"24:" + String.format("%#04X", (byte)getOctetValue(24)) + "\t\tScale factor of first fixed surface\n" +
		"25-28:" + String.format("%#010X", getOctetValue(25, 28)) + "\tScaled value of first fixed surface\n" +
		"29:" + String.format("%#04X", (byte)(l6 = getOctetValue(29))) + "\t\tType of second fixed surface = " + codeTable45(l6) + "\n" +
		"30:" + String.format("%#04X", (byte)getOctetValue(30)) + "\t\tScale factor of second fixed surface\n" +
		"31-34:" + String.format("%#010X", getOctetValue(31, 34)) + "\tScaled value of second fixed surface\n" ;

		return retVal;
	}

	private String template40(){
		return "\t----template 4.0----\n" + template4048();
	}

	private String template48(){
		long l1,l2,l3,l4;

		String retVal = "\t----template 4.8----\n" + template4048() +
		"35-36:" + getOctetValue(35, 36) + "\t\tYear  Time of end of overall time interval\n" +
		"37:" + getOctetValue(37) + "\t\tMonth\n" +
		"38:" + getOctetValue(38) + "\t\tDay\n" +
		"39:" + getOctetValue(39) + "\t\tHour\n" +
		"40:" + getOctetValue(40) + "\t\tMinute\n" +
		"41:" + getOctetValue(41) + "\t\tSecond\n" +
		"42:" + getOctetValue(42) + "\t\tn - number of time range specifications describing the time intervals used to calculate the statistically processed field\n" +
		"43-46:" + getOctetValue(43, 46) + "\t\tTotal number of data values missing in statistical process\n" +
		"47:" + (l1 = getOctetValue(47)) + "\t\tStatistical process used to calculate the processed field from the field at each time incre- ment during the time range = " + codeTable410(l1) + "\n" +
		"48:" + (l2 = getOctetValue(48)) + "\t\tType of time increment between successive fields used in the statistical processing = " + codeTable411(l2) + "\n" +
		"49:" + (l3 = getOctetValue(49)) + "\t\tIndicator of unit of time for time range over which statistical processing is done = " + codeTable44(l3) + "\n" +
		"50-53:" + getOctetValue(50, 53) + "\t\tLength of the time range over which statistical processing is done, in units defined by the previous octet\n" +
		"54:" + (l4 = getOctetValue(54)) + "\t\tIndicator of unit of time for the increment between the successive fields used = " + codeTable44(l4) + "\n" +
		"55-58:" + getOctetValue(55, 58) + "\t\tTime increment between successive fields, in units defined by the previous octet\n" ;

		return retVal;
	}

	private String codeTable40(long i){
		String retVal = "not supported";
		switch((int)i){
			case 0: retVal = "Analysis or forecast at a horizontal level or in a horizontal layer at a point in time";
					break;
			case 8: retVal = "Average, accumulation, extreme values or other statistically processed values at a horizontal level or in a horizontal layer in a continuous or non-continuous time interval";
					break;
		}
		return retVal;
	}

	private String codeTable41(long i){
		String retVal = "not supported";
		switch((int)i){
		case 0:	retVal = "Temperature";
				break;
		case 1:	retVal = "Moisture";
				break;
		case 2: retVal = "Momentum";
				break;
		case 3: retVal = "Mass";
				break;
		case 6: retVal = "Cloud";
				break;
		}
		return retVal;
	}

	private String codeTable42(long i, long cat) {
		String retVal = "not supported";
		switch ((int) cat) {
		case 0: // Temperature
			switch ((int) i) {
			case 0:
				retVal = "Temperature (K)";
				break;
			}
			break;
		case 1: // Moisture
			switch ((int) i) {
			case 1:
				retVal = "Relative humidity (%)";
				break;
			case 8:
				retVal = "Total precipitation* (kg m–2)";
				break;
			}
			break;
		case 2: // Momentum
			switch ((int) i) {
			case 0:
				retVal = "Wind direction (degree true)";
				break;
			case 1:
				retVal = "Wind speed (ms-1)";
				break;
			case 2:
				retVal = "u-component of wind (ms-1)";
				break;
			case 3:
				retVal = "v-component of wind (ms-1)";
				break;
			}
			break;
		case 3: // Mass
			switch ((int) i) {
			case 0:
				retVal = "Pressure (Pa)";
				break;
			case 1:
				retVal = "Pressure reduced to MSL (Pa)";
				break;
			}
			break;
		case 6: // Cloud
			switch ((int) i) {
			case 1:
				retVal = "Total cloud cover (%)";
				break;
			case 3:
				retVal = "Low cloud cover* (%)";
				break;
			case 4:
				retVal = "Medium cloud cover* (%)";
				break;
			case 5:
				retVal = "High cloud cover* (%)";
				break;

			}
			break;
		}

		return retVal;
	}


	private String codeTable43(long i){
		String retVal = "not supported";
		switch((int)i){
		case 1: retVal = "Analysis";
			break;
		case 2: retVal = "Forecast";
					break;
		}
		return retVal;
	}

	private String codeTable44(long i) {
		String retVal = "not supported";
		switch ((int) i) {
		case 1:
			retVal = "Hour";
			break;
		}
		return retVal;
	}

	private String codeTable45(long i) {
		String retVal = "not supported";
		switch ((int) i) {
		case 1:
			retVal = "Ground or water surface";
			break;
		case 101:
			retVal = "Mean sea level";
			break;
		case 103:
			retVal = "Specified height level above ground (m)";
			break;
		case 255:
			retVal = "Missing";
			break;
		}
		return retVal;
	}

	private String codeTable410(long i) {
		String retVal = "not supported";
		switch ((int) i) {
		case 1:
			retVal = "Accumulation";
			break;
		}
		return retVal;
	}

	private String codeTable411(long i) {
		String retVal = "not supported";
		switch ((int) i) {
		case 2:
			retVal = "Successive times processed have same start time of forecast, forecast time is incremented";
			break;
		}
		return retVal;
	}
}

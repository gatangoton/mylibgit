package take.myUtility.cygwin;

public abstract class Section {
	private byte[] data;

	public static Section createSection(byte data[]) throws Exception{
		Section retVal = null;

		switch(Section.sectionNo(data)){
		case 0:	retVal = new Section0(data); break;
		case 1: retVal = new Section1(data); break;
		//case 2: retVal = new Section2(data); break;
		case 3: retVal = new Section3(data); break;
		case 4: retVal = new Section4(data); break;
		case 5: retVal = new Section5(data); break;
		case 6: retVal = new Section6(data); break;
		case 7: retVal = new Section7(data); break;
		case 8: retVal = new Section8(data); break;
		}

		return retVal;
	}

	public Section(byte[] b){
		data = b;
	}

	public byte[] getData(){
		return data;
	}

	public int getOctetValue(int i){
		int retVal = -1;

		if(i <= getData().length ){
			retVal = getData()[i-1] & 0xff;
		}

		return retVal;
	}

	public long getOctetValue(int i1, int i2){
		long retVal = -1;
		switch (i2 - i1 + 1){
		case 2:	retVal = Section.connect2(new byte[]{getData()[i1 - 1], getData()[i2 - 1]});
				break;
		case 4:	retVal = Section.connect4(new byte[]{getData()[i1 - 1], getData()[i1], getData()[i1 + 1], getData()[i2 - 1]});
				break;
		case 8:	retVal = Section.connect8(new byte[]{getData()[i1 - 1], getData()[i1], getData()[i1 + 1], getData()[i1 + 2], getData()[i1 + 3], getData()[i1 + 4], getData()[i1 + 5],getData()[i2 - 1]});
				break;
		}
		return retVal;
	}

	public long getOctetValueNegative(int i1, int i2){
		long l1 = getOctetValue(i1, i2);
		long retVal = l1;
		long tester = 0;
		switch (i2 - i1 + 1){
		case 2:
			tester = 0x8000L;	//2 octetの最上位bitが1
			break;
		case 4:
			tester = 0x80000000L;	//4 octetの最上位bitが1
			break;
		case 8:
			tester = 0x8000000000000000L;	//8 octetの最上位bitが1
			break;
		}
		if((l1 & tester) != 0){  //最上位bitが1
			retVal = (l1 ^ tester) * (-1L);	//最上位bitのみ反転
		}
		return retVal;
	}

	public long sectionLength(){
		return connect4(getData());
	}

	abstract public String toString();

	public int sectionNo(){
		return Section.sectionNo(getData());
	}


	/**
	 * sectionの始め5octetからsection noを求める。
	 * @param b - sectionの始まり5octet
	 * @return - section no
	 */
	public static int sectionNo(byte[] b){
		int retVal = -1;

		if(	(b[0] == 0x47)&&	//'G'
			(b[1] == 0x52)&&	//'R'
			(b[2] == 0x49)&&	//'I'
			(b[3] == 0x42)){	//'B'
			retVal = 0;
		}else if(
			(b[0] == 0x37)&&	//'7'
			(b[1] == 0x37)&&	//'7'
			(b[2] == 0x37)&&	//'7'
			(b[3] == 0x37)){	//'7'
			retVal = 8;
		}else{
			retVal = (int)b[4];
		}

		return retVal;
	}

	/**
	 * sectionの始め5octetから読み込む長さを求める。section 7は5octet、その他のsectionはsection長さとなる。
	 * @param b - sectionの始まり5octet
	 * @return - 読み込む長さ
	 */
	public static int numberOfRead(byte[] b){
		int retVal = -1;

		if (sectionNo(b) == 7){
			retVal = 5;
		}else{
			retVal = sectionLength(b);
		}

		return retVal;
	}

	/**
	 * sectionの始め5octetからsectionの長さを求める。section 1-7は始めの4octetとなる。
	 * @param b - sectionの始まり5octet
	 * @return - sectionの長さ
	 */
	public static int sectionLength(byte[] b){
		int retVal = -1;

		switch (sectionNo(b)){
		case 0: retVal = 16; break;
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7: retVal = (int)connect4(b); break;
		case 8: retVal = 4;
		}

		return retVal;
	}

	/**
	 * 8bitのならび2つ（16bit）をlongに合成する。
	 * @param d
	 * @return
	 */
	public static long connect2(byte d[]){
		long a0, a1;

		if(d.length < 2){
			return 0;
		}
		a0 = ((long)(d[0] & 0xff)) << 8;
		a1 = ((long)(d[1] & 0xff));

		return a0 + a1;
	}

	/**
	 * 8bitのならび4つ（32bit）をlongに合成する。intにすると補数をとってしまうため、longにする。
	 * @param d
	 * @return
	 */
	public static long connect4(byte d[]){
		long a0, a1, a2, a3;

		if(d.length < 4){
			return 0;
		}
		a0 = ((long)(d[0] & 0xff)) << 24;
		a1 = ((long)(d[1] & 0xff)) << 16;
		a2 = ((long)(d[2] & 0xff)) << 8;
		a3 = ((long)(d[3] & 0xff));

		return a0 + a1 + a2 + a3;
	}

	/**
	 * 8bitのならび8つ（64bit）をlongに合成する。機械的にならべるだけ。最上位bitが1であれば負となる。
	 * @param d
	 * @return
	 */
	public static long connect8(byte d[]){
		long a0, a1, a2, a3, a4, a5, a6, a7;

		if(d.length < 8){
			return 0;
		}
		a0 = ((long)(d[0] & 0xff)) << 56;
		a1 = ((long)(d[1] & 0xff)) << 48;
		a2 = ((long)(d[2] & 0xff)) << 40;
		a3 = ((long)(d[3] & 0xff)) << 32;
		a4 = ((long)(d[4] & 0xff)) << 24;
		a5 = ((long)(d[5] & 0xff)) << 16;
		a6 = ((long)(d[6] & 0xff)) << 8;
		a7 = ((long)(d[7] & 0xff));

		return a0 + a1 + a2 + a3 + a4 + a5 + a6 + a7;
	}
}

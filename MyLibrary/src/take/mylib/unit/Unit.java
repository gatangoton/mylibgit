package take.mylib.unit;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unit {
	/*
	 * SI単位系を基準とする。
	 * m:
	 * kg:
	 * s:
	 * A:
	 * K:
	 * mol:
	 * cd:
	 */

	/*
	 * SIへのテーブル
	 */
	static private String[][]
			nonSIData ={{"in",	"0.0254*m"},	//長さ
					{"ft",	"0.3048*m"},
					{"sm",	"1609.344*m"},
					{"NM",	"1852*m"},
					{"lb",	"0.45359237*kg"},	//質量
					{"oz",	"0.028349523125*kg"},
					{"g",	"0.001*kg"},
					{"h",	"3600*s"},			//時間
					{"min",	"60*s"},
					{"kt",	"1/3600*NM/s"},		//速度
					{"fpm",	"ft/min"},
					{"G",	"9.80665*m/s/s"},	//加速度
					{"Gal",	"0.01*m/s/s"},
					{"N",	"kg*m/s/s"},		//力
					{"kgf",	"kg*G"},
					{"lbf",	"lb*G"},
					{"Pa",	"N/m/m"},			//圧力
					{"atm",	"101325*Pa"},
					{"psi",	"lbf/in/in"},
					{"Torr","101325/760*Pa"},
					{"mmHg","Torr"},
					{"inHg","in*/mm*mmHg"},
					};
	static private String[][]
			prefixData ={{"Y",	"24"},
					{"Z",	"21"},
					{"E",	"18"},
					{"P",	"15"},
					{"T",	"12"},
					{"G",	"9"},
					{"M",	"6"},
					{"k",	"3"},
					{"h",	"2"},
					{"d",	"-1"},
					{"c",	"-2"},
					{"m",	"-3"},
					{"u",	"-6"},
					{"n",	"-9"},
					{"p",	"-12"},
					{"f",	"-15"},
					{"a",	"-18"},
					{"z",	"-21"},
					{"y",	"-24"}};

	private HashMap<String, String> nonSITable, prefixTable;

	/**
	 * SIの単位のregexを作る。
	 * @return si="(m|kg|s|A|K|mol|cd)"
	 */
	static private String getSiAsRegex(){
		return "(m|kg|s|A|K|mol|cd)";
	}

	/**
	 * SI以外の単位のregexを作る。
	 * @return "(ft|NM|kt|.....)"
	 */
	static private String getNonSiAsRegex(){
		StringBuffer retVal = new StringBuffer();

		for(String s[] : Unit.nonSIData){
			retVal.append("|").append(s[0]);
		}
		return retVal.replace(0, 1, "(").append(")").toString();
	}

	/**
	 * prefixのついたSIを表すregexを作る。
	 * @return "(M|k|m|.....)(m|kg|s|...)"
	 */
	static private String getPrefixSiAsRegex(){
		StringBuffer retVal = new StringBuffer();

		for(String s[] : Unit.prefixData){
			retVal.append("|").append(s[0]);
		}
		return retVal.replace(0, 1, "(").append(")").toString()
				+ Unit.getSiAsRegex();
	}

	/**
	 *  prefixのついたSI以外の単位を表すregexを作る。
	 *  "kg"はSIであり、特別に除外する。
	 *  @return "(?!kg)(M|k|m|.....)(ft|NM|kt|.....)"
	 */
	static private String getPrefixNonSiAsRegex(){
		StringBuffer retVal = new StringBuffer();

		for(String s[] : Unit.prefixData){
			retVal.append("|").append(s[0]);
		}
		return retVal.replace(0, 1, "(?!kg)(").append(")").toString()
				+ Unit.getNonSiAsRegex();
	}

	static public boolean isSi(String s){
		//return Pattern.compile(Unit.getSiAsRegex()).matcher(s).matches();
		return Pattern.matches(Unit.getSiAsRegex(), s);
	}

	static public boolean isNonSi(String s){
		//return Pattern.compile(Unit.getNonSiAsRegex()).matcher(s).matches();
		return Pattern.matches(Unit.getNonSiAsRegex(), s);
	}

	static public boolean isPrefixSi(String s){
		//return Pattern.compile(Unit.getPrefixSiAsRegex()).matcher(s).matches();
		return Pattern.matches(Unit.getPrefixSiAsRegex(), s);
	}

	static public boolean isPrefixNonSi(String s){
		//return Pattern.compile(Unit.getPrefixNonSiAsRegex()).matcher(s).matches();
		return Pattern.matches(Unit.getPrefixNonSiAsRegex(), s);
	}

	public Unit(){
		nonSITable = new HashMap<String, String>();
		prefixTable = new HashMap<String, String>();
		for(String[] s:Unit.nonSIData){
			nonSITable.put(s[0], s[1]);
		}
		for(String[] s:Unit.prefixData){
			prefixTable.put(s[0], s[1]);
		}
	}

	/**
	 * 係数。Unit.nonSIDataまたはUnit.prefixDataで示されるもの。<br>
	 * 例<br>
	 * "m"	→"1"<br>
	 * "ft"	→"0.3048*m/ft"  テーブルに従う<br>
	 * "km"	→"1000*m/km"<br>
	 * "kmol"	→"1000*mol/kmol"<br>
	 * sがSI、非SI、prefixSI、prefix非SIならUnitStringを返す。条件に当てはまらないとnullを返す。
	 * @param s 対象となるString
	 * @return Unit.nonSIDataで定義された（値、単位、sの逆数）の積
	 */
	static public String convertOnce(String s){
		Unit u = new Unit();
		String retVal = "";
		Matcher m;

		if(Unit.isSi(s)){
			retVal = "1";
		}else if(Unit.isNonSi(s)){
			retVal = u.getNonSITable().get(s) + "/" + s;
		}else if((m = Pattern.compile(Unit.getPrefixSiAsRegex()).matcher(s)).matches()){	//isPrefixSI()
			retVal = String.valueOf(Math.pow(10, Integer.parseInt(u.getPrefixTable().get(m.group(1)))))
					+ "*" + m.group(2) + "/" + s;
		}else if((m = Pattern.compile(Unit.getPrefixNonSiAsRegex()).matcher(s)).matches()){	//isPrefixNonSI()
			retVal = String.valueOf(Math.pow(10, Integer.parseInt(u.getPrefixTable().get(m.group(1)))))
					+ "*" + u.getNonSITable().get(m.group(2)) + "/" + s;
		}
		return retVal;
	}



	public HashMap<String, String> getNonSITable() {
		return nonSITable;
	}

	public HashMap<String, String> getPrefixTable() {
		return prefixTable;
	}
}

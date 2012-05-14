package take.mylib.unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnitString{

	private String s;

	public UnitString(StringBuffer sb){
		this(sb.toString());
	}

	public UnitString(String sb){
		s = new String(sb);
	}

	/**
	 * UnitStringどうしのかけ算
	 * @param us かけるUnitString
	 * @return かけ算の結果。UnitStringは新たに作られる。
	 */
	public UnitString multiply(UnitString us){
		return multiply(us.toString());
	}

	/**
	 * UnitStringどうしのかけ算
	 * @param s かける値
	 * @return かけ算の結果。UnitStringは新たに作られる。
	 */
	public UnitString multiply(String s){
		return new UnitString(toString() + "*" + s).getOrderedUnitString();
	}

	/**
	 * UnitStringどうしの割り算
	 * @param us1 this/us1 となる
	 * @return 割り算の結果。UnitStringは新たに作られる。
	 */
	public UnitString devidedBy(UnitString us){
		return devidedBy(us.toString());
	}

	/**
	 * UnitStringどうしの割り算
	 * @param us1 this/s となる
	 * @return 割り算の結果。UnitStringは新たに作られる。
	 */
	public UnitString devidedBy(String s){
		return new UnitString(toString() + "/(" + s + ")").getOrderedUnitString();
	}

	/**
	 * 単位をusに変換する。係数の計算はしない。
	 * @param us 変換目標となる単位
	 * @return
	 */
	public UnitString convertTo(UnitString us){
		return this.multiply(unitStringForConversion(this, us));
	}

	/**
	 * 単位を変換する。
	 * @param d s1によるdouble値
	 * @param s1 変換前の単位
	 * @param s2 変換後の単位
	 * @return s2によるdouble値
	 */
	public static double convertTo(double d, String s1, String s2){
		return d * convertCoefficient(s1, s2);
	}

	/**
	 * 単位を変換する。
	 * @param s1 変換前の単位
	 * @param s2 変換後の単位
	 * @return s2による表現のString値
	 */
	public static String convertTo(String s1, String s2){
		return new UnitString(s1).convertTo(new UnitString(s2)).toString();
	}

	/**
	 * 単位を変換するための係数を求める。
	 * @param s1 変換前の単位
	 * @param s2 変換後の単位
	 * @return 変換のための係数double値
	 */
	public static double convertCoefficient(String s1, String s2){
		UnitString us1 = new UnitString(s1);
		UnitString us2 = new UnitString(s2);
		return us1.convertTo(us2).coefficient();
	}

	/**
	 * UnitStringの中にある、数値の計算。
	 * @return 計算結果のdouble値
	 */
	public double coefficient(){
		UnitString us = this.getOrderedUnitString();

		ArrayList<String> child = new ArrayList<String>();
		ArrayList<String> mother= new ArrayList<String>();
		double retVal = 1d;

		Matcher m = Pattern.compile("(^|[*/])([^*/]+)(?=[*/]|$)").matcher(new StringBuffer(extractUnit(us.toString())));
		while(m.find()){
			if(m.group(1).equals("*") || m.group(1).equals("")){
				if(!m.group(2).equals("1")){
					child.add(m.group(2));
				}
			}else{
				if(!m.group(2).equals("1")){
					mother.add(m.group(2));
				}
			}
		}

		/*約分と並び替え*/
		for(Object c : child.toArray()){
			if(mother.contains(c)){
				child.remove(c);
				mother.remove(c);
			}
		}
		if(child.size() == 0){
			child.add("1");
		}
		Collections.sort(child);
		Collections.sort(mother);

		for(String c : child){
			try{
				double d = Double.parseDouble(c);
				retVal *= d;
			}catch(NumberFormatException e){
			}
		}
		for(String mo : mother){
			try{
				double d = Double.parseDouble(mo);
				retVal /= d;
			}catch(NumberFormatException e){
			}
		}
		return retVal;
	}

	/**
	 * SI単位に換算、べき乗、括弧展開、約分を行う。係数の計算は行わない。
	 * @return 換算されたUnitString
	 */
	public UnitString getSIUnitString(){
		UnitString retVal = new UnitString("1");
		String s;

		Matcher m = Pattern.compile("(^|\\*|/)([()^a-zA-Z0-9+-.]+)(?=[*/]|$)")
						.matcher(orderingUnit(toString()));
		while(m.find()){
			if((!Pattern.matches("[a-zA-Z]+", m.group(2))) || Unit.isSi(m.group(2))){
				s = m.group(2);
			}else{
				s = new UnitString(Unit.convertOnce(m.group(2)))
						.multiply(m.group(2))
						.getOrderedUnitString()
						.getSIUnitString().toString();
			}
			if(m.group(1).equals("/")){
				retVal = retVal.devidedBy(s);
			}else{
				retVal = retVal.multiply(s);
			}
		}
		return retVal;
	}


	/**
	 * 約分、*1/1の削除、並び替えを行う。結果は新たにUnitStringを作る。
	 * @return UnitStringは新たに作られる。
	 */
	public UnitString getOrderedUnitString(){
		return new UnitString(orderingUnit(toString()));
	}

	/**
	 * べき乗、括弧整理、約分、*1/1の削除、並び替えを行う。ほとんどstaticメソッドの動き。
	 * @param units Stringによる表現。
	 * @return そのString
	 */
	private String orderingUnit(String units){
		ArrayList<String> child = new ArrayList<String>();
		ArrayList<String> mother= new ArrayList<String>();
		StringBuffer retVal = new StringBuffer();

		Matcher m = Pattern.compile("(^|[*/])([^*/]+)(?=[*/]|$)").matcher(new StringBuffer(extractUnit(units)));
		while(m.find()){
			if(m.group(1).equals("*") || m.group(1).equals("")){
				if(!m.group(2).equals("1")){
					child.add(m.group(2));
				}
			}else{
				if(!m.group(2).equals("1")){
					mother.add(m.group(2));
				}
			}
		}

		/*約分と並び替え*/
		for(Object c : child.toArray()){
			if(mother.contains(c)){
				child.remove(c);
				mother.remove(c);
			}
		}
		if(child.size() == 0){
			child.add("1");
		}
		Collections.sort(child);
		Collections.sort(mother);

		for(String c : child){
			retVal.append("*" + c);
			}
		for(String mo : mother){
			retVal.append("/" + mo);
		}
		return retVal.deleteCharAt(0).toString();
	}


	/*
	 * べき乗と()をとって、記号は*と/だけにする。
	 */
	private String extractUnit(String units){
		int index = 0;
		Pattern p;
		Matcher m;
		StringBuffer orig = new StringBuffer(units);

		/*べき乗の処理  )^2やkg^2を見つける*/
		p = Pattern.compile("(\\)|[a-zA-Z]+|[0-9]+.?[0-9]*|[0-9]+.?[0-9]*[eE][+-]?[0-9]+)\\^([+|-]?[0-9]+)");
		m = p.matcher(orig);
		while(m.find()){
			if(m.group(1).equals(")")){
				index = startBracketIndex(orig, m.end(1));
				orig.replace(index,
							m.end(),
							extractExponent(orig.substring(index, m.end(1)), m.group(2)));
			}else{
				orig.replace(m.start(),m.end(), extractExponent(m.group(1), m.group(2)));
			}
			m=p.matcher(orig);
		}

		/*括弧をとる*/
		p = Pattern.compile("^\\([^()]*\\)|.\\([^()]*\\)");//行頭の()と.()
		m = p.matcher(orig);
		while(m.find()){
			orig.replace(m.start(),m.end(), removeBracket((m.group())));
			m=p.matcher(orig);
		}
		return orig.toString();
	}

	private int startBracketIndex(StringBuffer orig, int endBracketIndex){
		int bracketNum = 1, index = endBracketIndex - 1;
		while(bracketNum > 0){
			index--;
			if(orig.charAt(index) == ')'){
				bracketNum++;
			}
			if(orig.charAt(index) == '('){
				bracketNum--;
			}
		}
		return index;
	}

	private String extractExponent(String base, String exponent){
		int i, expAbs, exp;
		StringBuffer temp = new StringBuffer();

		exp = Integer.parseInt(exponent);
		expAbs = (exp < 0) ? exp * (-1): exp;
		for(i=1; i <= expAbs; i++){
			temp.append("*");
			temp.append(base);
		}
		temp.delete(0, 1);
		temp.insert(0,"(");
		temp.append(")");
		if(exp < 0){
			temp.insert(0, "(1/");
			temp.append(")");
		}
		return temp.toString();
	}

	private String removeBracket(String str){
		StringBuffer strBuff = new StringBuffer(str);
		Pattern p;
		Matcher m;

		switch(strBuff.charAt(0)){
		case '*':
		case '(':
			strBuff.deleteCharAt(strBuff.indexOf("("));
			strBuff.deleteCharAt(strBuff.indexOf(")"));
			break;
		case '/':
			p = Pattern.compile("(\\/|\\*)");
			m = p.matcher(strBuff);
			while(m.find()){
				 if(m.group().equals("/")){
					 strBuff.replace(m.start(), m.end(), "*");
				 }else{
					 strBuff.replace(m.start(), m.end(), "/");
				 }
			}
			strBuff.replace(0, 1, "/");
			strBuff.deleteCharAt(strBuff.indexOf("("));
			strBuff.deleteCharAt(strBuff.indexOf(")"));
			break;
		}
		return strBuff.toString();
	}


	/**
	 * us1 * return = us2 となるようなreturnを返す。<br>
	 * 単位はus2にそろえる。従って、(us1/us2)(us2/us1)となり、第1項については係数となる。
	 *
	 * @param us1
	 * @param us2
	 * @return
	 */
	private UnitString unitStringForConversion(UnitString us1, UnitString us2){
		return us1.devidedBy(us2).getSIUnitString()
			.multiply(us2.devidedBy(us1));
	}

	public String toString() {
		return s;
	}
}

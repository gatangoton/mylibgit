package take;

import take.mylib.unit.UnitString;

public class Practice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello world");

		UnitString us = new UnitString((""));
		UnitString us1 = new UnitString(("kt"));
		UnitString us2 = new UnitString(("m/s"));

		System.out.println(UnitString.convertTo(3000, "psi", "atm"));
		System.out.println(UnitString.convertTo("NM", "ft*m"));
	}

}

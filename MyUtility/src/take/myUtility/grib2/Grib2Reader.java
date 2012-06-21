package take.myUtility.grib2;

public class Grib2Reader {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Grib2ReaderController mc = null;
		try {

			mc = new Grib2ReaderController();
			Grib2ReaderFrame mf = new Grib2ReaderFrame(mc);
			mc.setMf(mf);

			mf.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package take.myUtility.cygwin;

public class Grib2Reader {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			Grib2MainController mc = new Grib2MainController();
			Grib2MainFrame mf = new Grib2MainFrame(mc);
			mc.setMf(mf);

			mf.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

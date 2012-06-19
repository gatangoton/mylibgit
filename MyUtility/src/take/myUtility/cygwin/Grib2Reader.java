package take.myUtility.cygwin;

public class Grib2Reader {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Grib2MainController mc = null;
		try {

			mc = new Grib2MainController();
			Grib2MainFrame mf = new Grib2MainFrame(mc);
			mc.setMf(mf);

			mf.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

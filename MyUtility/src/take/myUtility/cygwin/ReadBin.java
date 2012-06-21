package take.myUtility.cygwin;

public class ReadBin {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			ReadBinController mc = new ReadBinController();
			ReadBinFrame mf = new ReadBinFrame(mc);
			mc.setMf(mf);

			mf.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

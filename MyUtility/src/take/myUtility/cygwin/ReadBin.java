package take.myUtility.cygwin;

public class ReadBin {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			MainController mc = new MainController();
			MainFrame mf = new MainFrame(mc);
			mc.setMf(mf);

			mf.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

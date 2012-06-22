package take.myUtility.bumpy;

public class BumpyInterface implements Runnable{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//new Thread(new BumpyInterface()).start();
		new BumpyInterface().run();
	}

	@Override
	public void run() {
		BumpyInterfaceController mc = null;
		try {
			mc = new BumpyInterfaceController();
			BumpyInterfaceFrame mf = new BumpyInterfaceFrame(mc);
			mc.setMf(mf);

			mf.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

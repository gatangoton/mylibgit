package take.myUtility.grib2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grib2MainController implements ActionListener{
	private Grib2MainFrame mf;
	private Grib2 g2;

	public Grib2MainController(){

	}

	public void setMf(Grib2MainFrame mf) {
		this.mf = mf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			g2 = new Grib2(mf.getFileName());
			mf.getTextArea().setText(g2.toString());

		} catch (Exception e3) {
		// TODO 自動生成された catch ブロック
		e3.printStackTrace();
		}
	}

	public Grib2 getGrib2(){
		return g2;
	}

}

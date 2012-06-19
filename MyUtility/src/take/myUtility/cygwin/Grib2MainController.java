package take.myUtility.cygwin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		FileInputStream fis;
		int i;
		try {
			g2 = new Grib2(mf.getFileName());
			System.out.println(g2.toString());
			mf.getTextArea().setText(g2.toString());

		} catch (Exception e3) {
		// TODO 自動生成された catch ブロック
		e3.printStackTrace();
		}


	}

	public Grib2 getGrib2(){
		return g2;
	}
	private String chap(int d[]){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < d.length; i++){
			sb.append(Integer.toHexString(d[i]));
			sb.append(" ");
		}
		sb.append("\n");
		return sb.toString();
	}
}

package take.myUtility.cygwin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Grib2MainController implements ActionListener{
	private Grib2MainFrame mf;

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
			fis = new FileInputStream(new File(mf.getFileName()));
			int[] chap0 = new int[16];
			int[] chap1 = new int[21];
			int[] chap2 = new int[0];
			int[] chap3 = new int[72];
			int[] chap4 = new int[16];
			int[] chap5 = new int[21];
			for(i = 0; i <= 15; i++){
				chap0[i] = (int)fis.read();
			}
			for(i = 0; i <= 20; i++){
				chap1[i] = (int)fis.read();
			}
			for(i = 0; i <= 71; i++){
				chap3[i] = (int)fis.read();
			}
			for(i = 0; i <= 20; i++){
				chap5[i] = (int)fis.read();
			}

			mf.getTextPane().setText(chap(chap0)
					+chap(chap1)
					+chap(chap2)
					+chap(chap3)
					+chap(chap4)
					+chap(chap5));
			
			//mf.getTextPane_1().setText(sb1.toString());
			fis.close();

		} catch (FileNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO 自動生成された catch ブロック
			e2.printStackTrace();
		}


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

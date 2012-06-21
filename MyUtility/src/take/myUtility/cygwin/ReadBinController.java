package take.myUtility.cygwin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ReadBinController implements ActionListener{
	private ReadBinFrame mf;

	public ReadBinController(){

	}

	public void setMf(ReadBinFrame mf) {
		this.mf = mf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(mf.getFileName()));
			for(int i = 0; i<= 100; i++){
				int ch = fis.read();
				char ch1 = (char)ch;
				sb.append(Integer.toHexString(ch));
				switch (ch1){
				case '\n':	sb.append("\n");
							sb1.append(ch1);
							break;
				case '\t':
				default :	sb.append(" ");
							sb1.append(ch1);
							sb1.append("  ");
				}


			}
			mf.getTextArea().setText(sb.toString());
			mf.getTextArea_1().setText(sb1.toString());
			fis.close();

		} catch (FileNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO 自動生成された catch ブロック
			e2.printStackTrace();
		}


	}
}

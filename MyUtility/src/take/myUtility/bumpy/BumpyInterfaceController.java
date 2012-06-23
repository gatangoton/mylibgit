package take.myUtility.bumpy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import take.myUtility.cygwin.BufferedReaderRunnable;
import take.myUtility.cygwin.CygwinOnJava;
import take.mylib.ErrInformee;

public class BumpyInterfaceController implements ActionListener, Observer, ErrInformee{
	public static final String curdir = "c:\\cygwin\\home\\taketo\\temp";

	private BumpyInterfaceFrame bif;

	public void setMf(BumpyInterfaceFrame f) {
		bif = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CygwinOnJava cyg = new CygwinOnJava(this, curdir);
		//cyg.command("bash", "d.sh");
		cyg.addErrInformee(this);
		cyg.command(bif.getCommandText());
	}

	@Override
	synchronized public void update(Observable o, Object arg) {
		switch(((BufferedReaderRunnable)o).getType()){
		case BufferedReaderRunnable.STDOUT :
			bif.appendStdOut((String) arg);
			break;
		case BufferedReaderRunnable.STDERR:
			bif.appendErrOut((String)arg);
			break;
		}


	}

	@Override
	public void errOccured(Exception e, Object o, String msg) {
		bif.getPanelErr().setText(
			o.toString() + ":" + msg + "\n" + e.toString() + "\n");
	}
}

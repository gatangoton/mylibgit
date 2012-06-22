package take.myUtility.bumpy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import take.myUtility.cygwin.BufferedReaderRunnable;
import take.myUtility.cygwin.CygwinOnJava;

public class BumpyInterfaceController implements ActionListener, Observer{

	private BumpyInterfaceFrame bif;

	public void setMf(BumpyInterfaceFrame f) {
		bif = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(bif.getCommandText());
		CygwinOnJava cyg = new CygwinOnJava(this, "c:\\cygwin\\home\\taketo\\temp");
		cyg.command("bash", "d.sh");
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
}

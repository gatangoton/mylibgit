package take.myUtility.cygwin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

public class BufferedReaderRunnable extends Observable implements Runnable {
	public static final int STDOUT = 1;
	public static final int STDERR = 2;

	private CygwinOnJava cyg;
	private int type;
	private BufferedReader br;

	BufferedReaderRunnable(CygwinOnJava c, int type){
		this.cyg = c;
		this.type = type;
	}

	@Override
	public void run() {
		String s ;

		switch(type){
		case 1:
			br = new BufferedReader(new InputStreamReader(cyg.getProcess().getInputStream()));
			break;
		case 2:
			br = new BufferedReader(new InputStreamReader(cyg.getProcess().getErrorStream()));
			break;
		}
		try {
			while ((s = br.readLine()) != null) {
				this.setChanged();
				this.notifyObservers(s + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getType(){
		return this.type;
	}

}

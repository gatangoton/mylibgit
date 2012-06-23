package take.myUtility.cygwin;

import java.io.File;
import java.io.IOException;
import java.util.Observer;

import take.mylib.ErrInformerClass;

/**
 *  コマンドライン、主にCygwinの操作を目的としたクラス。<br>
 *
 *  CygwinOnJava coj = new CygwinOnJava(aObserver, aString);<br>
 *  coj.command("bash", "a.sh");<br>
 *
 *  標準出力、標準エラー出力はそれぞれThreadを作ってそこからaObserverにupdate()でnotifyAll()される。
 *  aObserverはupdate()を実装する。
 *
 * @author taketo
 *
 */
public class CygwinOnJava extends ErrInformerClass implements Runnable{
	private String currentDir;
	private ProcessBuilder pb;
	private BufferedReaderRunnable stdOutBrr, stdErrBrr;
	private Process p = null;

	/**
	 *
	 * @param obs aObserverを指定しておくと、stdOutBrr, stdErrBrrからupadate()が送られる。
	 * @param cd 作業用dirを指定する。"\"は"\\"と表現する必要がある。
	 */
	public CygwinOnJava(Observer obs, String cd){
		currentDir = cd;
		stdOutBrr = new BufferedReaderRunnable(this, BufferedReaderRunnable.STDOUT);
		stdErrBrr = new BufferedReaderRunnable(this, BufferedReaderRunnable.STDERR);
		stdOutBrr.addObserver(obs);
		stdErrBrr.addObserver(obs);
	}

	public void command(String... c){
		pb = new ProcessBuilder(c);
		pb.directory(new File(currentDir));
		new Thread(this).start();
	}

	public void run() {
		try {
			p = pb.start();
			Thread stdOutTh = new Thread(stdOutBrr);
			Thread stdErrTh = new Thread(stdErrBrr);
			stdOutTh.start();
			stdErrTh.start();
			stdOutTh.join();
			stdErrTh.join();
		} catch (IOException e) {
			this.errOccured(e, "unable to start ProcessBuilder");
		} catch (InterruptedException e) {
			this.errOccured(e, "unable to join Thread(stdOut or stdErr)");
		}finally{
			try {
				p.getErrorStream().close();
			} catch (IOException e) {
				this.errOccured(e, "unable to close ErrorStream");
			}

	        try {
				p.getOutputStream().close();
			} catch (IOException e) {
				this.errOccured(e, "unable to close OutputStream");

			}
			p.destroy();
		}
	}

	Process getProcess(){
		return p;
	}
}

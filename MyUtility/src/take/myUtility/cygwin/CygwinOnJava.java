package take.myUtility.cygwin;

import java.io.File;
import java.io.IOException;
import java.util.Observer;

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
public class CygwinOnJava implements Runnable{
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
			System.out.println("err exit");
		} catch (InterruptedException e) {
			System.out.println("err exit");

		}finally{
			try {
				p.getErrorStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}

	        try {
				p.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.destroy();
		}
	}

	Process getProcess(){
		return p;
	}
}

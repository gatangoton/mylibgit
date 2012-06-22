package take;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;



import take.mylib.unit.UnitString;

public class Practice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ProcessBuilder pb = new ProcessBuilder("bash","d.sh");
		Process p=null;
		File f=new File("c:\\cygwin\\home\\taketo\\temp");
		pb.directory(f);
		/*
		System.out.println(f);
		System.out.println("--------------------------------------");
		Map <String, String> m = pb.environment();
		for(String s : m.keySet()){
			System.out.println(s+"\t"+m.get(s));
		}
		System.out.println("--------------------------------------");
		pb.redirectErrorStream(true);
		*/
		try{
			//p = pb.start();
			p=Runtime.getRuntime().exec(new String[] {"bash", "c:\\cygwin\\home\\taketo\\temp\\d.sh"});

		InputStream in = p.getInputStream();
		//InputStream in2 = p.getErrorStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String s;
		int c,i=0;
		while((s = br.readLine()) != null){
			//System.out.println(i + ":" + String.format("%X", c));i++;
			System.out.println(s);

			//while (in2.read() != -1){}
		}

		}catch(IOException e){
			System.out.println("err exit");
			System.exit(-1);
		}

		p.destroy();
	}

}

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
		/*
		System.out.println("Hello world");

		UnitString us = new UnitString((""));
		UnitString us1 = new UnitString(("kt"));
		UnitString us2 = new UnitString(("m/s"));

		System.out.println(UnitString.convertTo(3000, "psi", "atm"));
		System.out.println(UnitString.convertTo("NM", "ft*m"));
		*/

		ProcessBuilder pb = new ProcessBuilder("bash","c.sh");
		Process p=null;
		File f=new File("c:\\cygwin\\home\\taketo\\temp");
		pb.directory(f);
		System.out.println(f);
		System.out.println("--------------------------------------");
		Map <String, String> m = pb.environment();
		for(String s : m.keySet()){
			System.out.println(s+"\t"+m.get(s));
		}
		System.out.println("--------------------------------------");
		pb.redirectErrorStream(true);
		try{
			p = pb.start();

		InputStream in = p.getInputStream();
		//InputStream in2 = p.getErrorStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String s;
		while((s=br.readLine()) != null){
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

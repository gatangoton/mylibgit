package take.myUtility.cygwin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeMap;

public class Grib2 {
	String fileName;
	TreeMap<Integer, Section> sections;

	public Grib2(String s) throws Exception{
		fileName = s;
		sections = new TreeMap<Integer, Section>();
		BufferedInputStream bis = null;
		byte[] d, head;
		long pos = 1;
		int nor;

		try{
			bis = new BufferedInputStream(new FileInputStream(fileName));

			bis.read(d = new byte[16]);
			sections.put(new Integer((int)pos), Section.createSection(d));
			pos += 16;

			do{
				bis.mark(5);
				bis.read(head = new byte[5]);
				bis.reset();

				nor = Section.numberOfRead(head);
				bis.read(d = new byte[Section.numberOfRead(head)]);
				sections.put(new Integer((int)pos), Section.createSection(d));
				if(Section.sectionNo(head) == 7){
					for(long lo = Section.sectionLength(head) - Section.numberOfRead(head); lo > 0; lo--){
						bis.read();
					}
				}
				pos += Section.sectionLength(head);
			}while(Section.sectionNo(head) != 8);
			

			//System.out.println(this.toString());

			/*
System.out.println(sections.size() + "           " + pos);
Section0 s0 = ((Section0)sections.get(1));
Section s1 = ((Section)sections.get(17));
System.out.println(s0.toString() + s1.toString());
*/
		} catch(FileNotFoundException e1){
			throw new Exception("(FileNotFoundException");
		} catch(IOException e2){
			throw new Exception("(IOException");
		} catch(Exception e3){
			throw new Exception(e3.getMessage());
		} finally{
			if(bis != null){
				bis.close();
			}
		}
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Integer i : sections.navigableKeySet()){
			Section sec = sections.get(i);
			if(sec != null){
				sb.append(i.toString() + ":" + sections.get(i).toString());
			}
		}
		return sb.toString();
	
	}
	/**
	 * 8bitのならび4つ（32bit）をlongに合成する。intにすると補数をとってしまうため、longにする。
	 * @param d
	 * @return
	 */
	/*private long connect4(byte d[]){
		long a0, a1, a2, a3;

		if(d.length != 4){
			return 0;
		}
		a0 = ((long)(d[0] & 0xff)) << 24;
		a1 = ((long)(d[1] & 0xff)) << 16;
		a2 = ((long)(d[2] & 0xff)) << 8;
		a3 = ((long)(d[3] & 0xff));

		return a0 + a1 + a2 + a3;
	}*/
}

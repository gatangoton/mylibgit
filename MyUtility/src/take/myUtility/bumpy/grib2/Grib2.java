package take.myUtility.bumpy.grib2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.TreeSet;


public class Grib2 implements Comparator<Section>{
	String fileName;
	TreeSet<Section> sections;

	public Grib2(String fn) throws Exception{
		fileName = fn;
		sections = new TreeSet<Section>(this);
		BufferedInputStream bis = null;
		byte[] d, head;
		long pos = 1;
		int nor;
		Section s, ps, ts;

		try{
			bis = new BufferedInputStream(new FileInputStream(fileName));

			//Section 0 作成
			bis.read(d = new byte[16]);
			sections.add(Section.createSection(d, pos));
			pos += 16;

			//Section 1-8 作成
			do{
				bis.mark(5);
				bis.read(head = new byte[5]);
				bis.reset();

				bis.read(d = new byte[nor = Section.numberOfRead(head)]);
				sections.add(s = Section.createSection(d, pos));
				if(s.sectionNo() == 7){	//section7のデータ部は空読み
					for(long lo = s.sectionLength() - nor; lo > 0; lo--){
						bis.read();
					}
				}

				ps = s;
				do{			//一つ前のsectionを探す
					ts = ps;
					ps = sections.lower(ts);
				}while(ps.sectionNo() >= s.sectionNo());
				ps.addChild(s);
				s.setParent(ps);

				pos += s.sectionLength();
			}while(Section.sectionNo(head) != 8);

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
		for(Section s : sections){
			sb.append(s.getPosition() + ":" + s.toString());
		}
		return sb.toString();
	}

	@Override
	public int compare(Section o1, Section o2) {
		return (int)(o1.getPosition() - o2.getPosition());
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

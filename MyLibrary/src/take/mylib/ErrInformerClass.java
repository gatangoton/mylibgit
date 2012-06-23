package take.mylib;

import java.util.TreeSet;

public class ErrInformerClass implements ErrInformer{
	private TreeSet<ErrInformee> s;

	public ErrInformerClass(){
		s = new TreeSet<ErrInformee>();
	}

	@Override
	public void addErrInformee(ErrInformee o){
		s.add(o);
	}

	@Override
	public void errOccured(Exception e, String msg){
		for(ErrInformee ei : s){
			ei. errOccured(e, this, msg);
		}
	}
}

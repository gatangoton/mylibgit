package take.mylib;

/**
 * Exceptionを予めaddされたaErrIngormeeにerrOccured()で知らせる。AdaptorとしてErrInformerClassがある。
 * @author taketo
 *
 */
public interface ErrInformer {
	public void addErrInformee(ErrInformee o);
	public void errOccured(Exception e, String msg);

	/*
	------------------実装例-------------------
	class Example implements ErrInformer {
		TreeSet<ErrInformee> s;

		public Example(){
			s = new TreeSet<ErrInformee>();
		}

		public void addErrInformee(ErrInformee o){
			s.add(o);
		}

		public void errOccured(Exception e, String msg){
			for(ErrInformee ei : s){
				ei. errOccured(e, this, msg);
			}
		}
	}
	*/
}

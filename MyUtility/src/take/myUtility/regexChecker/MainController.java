package take.myUtility.regexChecker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JButton;

public class MainController implements ActionListener {
	private static String btnMatces = "matches";
	private static String btnFind = "find";

	private MainFrame mf;

	public MainController(MainFrame m){
		super();
		mf = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnText = (((JButton)(e.getSource())).getText());
		if(btnText.equals(btnMatces)){
			matcesClicked();
		}else if(btnText.equals(btnFind)){
			findClicked();
		}

	}


	private void matcesClicked(){
		try{
			mf.showResult(String.valueOf((Pattern.matches(mf.getRegex(), mf.getString()))));
		}catch(PatternSyntaxException ex){
			mf.showResult(ex.toString());
		}
	}

	private void findClicked(){
		String res = "";
		int i = 0;
		Matcher m = Pattern.compile(mf.getRegex()).matcher(mf.getString());

		try{
			while(m.find()){
				res = res + String.valueOf(++i) + ":\t" + mf.getString() + "\n";
				res = res + "\t" + numberOf(" ", m.start()) + stringPointer(m.end() - m.start()) + "\n";
				res = res + "\t" + stringGroup(m) + "\n";
			}
		}catch(PatternSyntaxException ex){
			res = ex.toString();
		}
		mf.showResult((res.equals("")) ? "no match" : res);
	}

	private String stringPointer(int num){
		StringBuffer retVal = new StringBuffer();

		switch (num){
		case 0: break;
		case 1:	retVal = new StringBuffer("^");
				break;
		default:	retVal.append("^");
					retVal.append(numberOf("-", num - 2));
					retVal.append("^");
		}
		return retVal.toString();
	}

	private String numberOf(String s, int ttl){
		StringBuffer rv = new StringBuffer("");
		for(int i = 0; i < ttl; i++){
			rv.append(s);
		}
		return rv.toString();
	}
	
	private String stringGroup(Matcher m){
		StringBuffer sb = new StringBuffer("group ");
		
		for(int i = 0; i <= m.groupCount(); i++){
			sb.append(String.valueOf(i) + ":" + m.group(i) + "\t");
		}
		return sb.toString();
	}

}

package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.Context;
import core.Value;

public class For extends FunctionBase {
	
	String _var;
	String _from;
	String _to;
	
	public For() {
		
	}
	
	public FunctionBase createObj() {
		return new For();
	}
	
	@Override
	public String getDetector() {
		return ".*for.* ([A-z0-9]+) .*in.* ([A-z0-9]+) .*to.* ([A-z0-9]+)";
	}
	
	@Override
	public boolean evaluate() {
		
		Pattern p = Pattern.compile(getDetector());
		Matcher m = p.matcher(_code);
		
		if( m.matches() ) {
			_var = m.group(1);
			_from = m.group(2);
			_to = m.group(3);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public void run(Context context) {
		
	}
}

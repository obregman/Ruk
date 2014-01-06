package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.Context;
import core.Value;

public class Print extends FunctionBase {
	
	String _str;
	
	public Print() {
		
	}
	
	public FunctionBase createObj() {
		return new Print();
	}
	
	@Override
	public String getDetector() {
		return ".*print.* ([A-z0-9]+)";
	}
	
	@Override
	public boolean evaluate() {
		
		Pattern p = Pattern.compile(getDetector());
		Matcher m = p.matcher(_code);
		
		if( m.matches() ) {
			_str = m.group(1);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public void run(Context context) {
		
		if( context.variableExists(_str) )
			System.out.println(context.getVariable(_str).toString());
		else
			System.out.println(_str);
	}
}

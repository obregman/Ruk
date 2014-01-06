package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.Context;
import core.Value;

public class If extends FunctionBlock {
	
	String _varL;
	String _varR;
	String _condition;
	FunctionBlock _block;
	
	public If() {
		
	}
	
	public FunctionBase createObj() {
		return new If();
	}
	
	@Override
	public String getDetector() {
		return ".*if.* ([A-z0-9]+).*(==|!=|<|>).*([A-z0-9]+) .*than.*";
	}
	
	@Override
	public boolean evaluate() {
		
		Pattern p = Pattern.compile(getDetector());
		Matcher m = p.matcher(_code);
		
		if( m.matches() ) {
			_varL = m.group(1);
			_condition = m.group(2);
			_varR = m.group(3);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public RunResults run(Context context) {
		return RunResults.Success;
	}
}

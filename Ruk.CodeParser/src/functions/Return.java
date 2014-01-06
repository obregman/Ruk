package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.CodingRules;
import core.Context;
import core.Value;

public class Return extends FunctionBase {
	
	String _retVal;
	
	public Return() {
		
	}
	
	public FunctionBase createObj() {
		return new Return();
	}
	
	@Override
	public String getDetector() {
		return ".*return.* ([A-z0-9]+)";
	}
	
	@Override
	public boolean evaluate() {
		
		Pattern p = Pattern.compile(getDetector());
		Matcher m = p.matcher(_code);
		
		if( m.matches() ) {
			_retVal = m.group(1).trim();
		}
		
		return false;
	}
	
	@Override
	public RunResults run(Context context) {
		
		if( !CodingRules.canBeAVariable(_retVal) ) {
			context.addError(String.format("Line %d: var1 is not a valid variable", _lineNum));
			return RunResults.Fail;
		}
		
		if( context.variableExists(_retVal) )
			context.setReturnValue(context.getVariable(_retVal));
		else
			context.setReturnValue(new Value(_retVal));
		
		return RunResults.Return;
	}
}

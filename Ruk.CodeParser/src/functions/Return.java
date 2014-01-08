package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.ParsingHelper;
import core.Context;
import core.RegexDic;
import core.Value;
import functions_base.FunctionBase;
import functions_base.RunResults;

public class Return extends FunctionBase {
	
	String _retVal;
	
	public Return() {
		
	}
	
	public FunctionBase createObj() {
		return new Return();
	}
	
	@Override
	public String getDetector() {
		return RegexDic.return_d;
	}
	
	@Override
	public boolean evaluate() {
		
		Pattern p = Pattern.compile(getDetector());
		Matcher m = p.matcher(_code);
		
		if( m.matches() ) {
			_retVal = m.group(1).trim();
		}
		else
			return false;
		
		return true;
	}
	
	@Override
	public RunResults run(Context context) {
		
		if( !ParsingHelper.canBeAVariable(_retVal) ) {
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

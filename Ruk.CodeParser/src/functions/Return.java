package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.ParsingHelper;
import core.Context;
import core.RegexDic;
import core.Value;
import expression.Expression;
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
		
		Value returnValue = Expression.evaluate(_retVal, context);
		
		if( returnValue == null ) {
			context.addError(String.format("Line %d: invalid expression [%s]", _lineNum, _retVal));
			return RunResults.Fail;
		}
		else
			context.setReturnValue(returnValue);
		
		return RunResults.Return;
	}
}

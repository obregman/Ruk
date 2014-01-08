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

public class Assign extends FunctionBase {
	
	String _var1;
	String _var2;
	
	public Assign() {
		
	}
	
	public FunctionBase createObj() {
		return new Assign();
	}
	
	@Override
	public String getDetector() {
		return RegexDic.assign_d;
	}
	
	@Override
	public boolean evaluate() {
		
		Pattern p = Pattern.compile(getDetector());
		Matcher m = p.matcher(_code);
		
		if( m.matches() ) {
			_var1 = m.group(1).trim();
			_var2 = m.group(2).trim();
		}
		else
			return false;
		
		return true;
	}
	
	@Override
	public RunResults run(Context context) {
		
		if( !ParsingHelper.canBeAVariable(_var1) ) {
			context.addError(String.format("Line %d: var1 is not a valid variable", _lineNum));
			return RunResults.Fail;
		}
		
		Value val = Expression.evaluate(_var2, context);
		if( val == null )
			context.addError(String.format("Line %d: error - invalid variable %s", _lineNum, _var2));	
		else
			context.updateVariable(_var1, val);
		
		return RunResults.Success;
	}
	
	
}

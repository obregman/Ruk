package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.CodingRules;
import core.Context;
import core.Value;

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
		return "([A-z][A-z0-9 ]*)[=]([A-z0-9 ]+)";
	}
	
	@Override
	public boolean evaluate() {
		
		Pattern p = Pattern.compile(getDetector());
		Matcher m = p.matcher(_code);
		
		if( m.matches() ) {
			_var1 = m.group(1).trim();
			_var2 = m.group(2).trim();
		}
		
		return false;
	}
	
	@Override
	public void run(Context context) {
		
		if( !CodingRules.canBeAVariable(_var1) ) {
			context.addError(String.format("Line %d: var1 is not a valid variable", _lineNum));
			return;
		}
		
		if( CodingRules.isNumeric(_var2) ) {
			context.updateVariable(_var1, new Value(Long.parseLong(_var2)));
		}
		else
			if( context.variableExists(_var2) ) {
				long lVal = context.getVariable(_var2).val();
				context.updateVariable(_var1, new Value(lVal));
			}
			else
				context.addError(String.format("Line %d: missing variable %s", _lineNum, _var2));
		
	}
}

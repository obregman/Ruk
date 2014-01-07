package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.Context;
import core.RegexDic;
import core.Value;
import core.Value.Types;

public class Expression {
	
	public static Value evaluate(String expr, Context context) {
		
		Value val = null;
		
		// Is a variable?
		if( context.variableExists(expr) )	{
			val = context.getVariable(expr);				
		}
		// String in quotes
		else
		{
			if( expr.matches(RegexDic.quotedString) ) {
				Pattern p = Pattern.compile(RegexDic.quotedString);
				Matcher m = p.matcher(expr);
				if( m.matches() ) {
					String str = m.group(1);
					val = new Value(str);
				}
			}
			else
			{
				// Composite expression (e.g. "hello " + str) - break down
				
			}			
		}
		
		return val;		
	}

}

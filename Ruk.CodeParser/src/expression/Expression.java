package expression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.Context;
import core.ParsingHelper;
import core.RegexDic;
import core.TextHelper;
import core.Value;
import core.Value.Types;

public class Expression {
	
	public static Value evaluate(String expr, Context context) {
		
		Value val = null;
		
		// Is numeric
		if( ParsingHelper.isNumeric(expr) ) {
			val = new Value(Integer.parseInt(expr));
		}
		else
		// Is a variable?
		if( context.variableExists(expr) )	{
			val = context.getVariable(expr);				
		}
		// String
		else
		if( expr.matches("[A-z0-9]+"))
		{
			val = new Value(expr);
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
				ExprTree tree = ExprTree.generateTree(expr);
				val = tree.evaluate(context);
			}			
		}
		
		return val;		
	}
	
	

}

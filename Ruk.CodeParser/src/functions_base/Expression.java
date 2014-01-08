package functions_base;

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
				// Break into parts
				List<String> parts = new ArrayList<String>();
				int pos = 0;
				while(true) {
					
					int start = TextHelper.nextRealCharacter(expr, pos);
					if( start < 0 )
						break;
					int end = nextEndCharacter(expr, start + 1);
					if( end < 0 )
						break;
					
					String part = expr.substring(start, end);
					parts.add(part);
					
					
				}
				
			}			
		}
		
		return val;		
	}
	
	private static int nextEndCharacter(String text, int from) {
		int pos = from;
		while(pos < text.length()) {
			char ch = text.charAt(pos);
			if( ch < 'A' && ch > 'z' && ch < '0' && ch > '9' )
				return pos;
			pos++;
		}
		return pos;
	}

}

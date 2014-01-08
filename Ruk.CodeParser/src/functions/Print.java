package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.Context;
import core.RegexDic;
import core.Value;
import expression.Expression;
import functions_base.FunctionBase;
import functions_base.RunResults;

public class Print extends FunctionBase {
	
	String _str;
	
	public Print() {
		
	}
	
	public FunctionBase createObj() {
		return new Print();
	}
	
	@Override
	public String getDetector() {
		return RegexDic.print_d;
	}
	
	@Override
	public boolean evaluate() {
		
		Pattern p = Pattern.compile(getDetector());
		Matcher m = p.matcher(_code);
		
		if( m.matches() ) {
			_str = m.group(1);			
		}
		else
			return false;
		
		return true;
	}
	
	@Override
	public RunResults run(Context context) {
		
		Value val = Expression.evaluate(_str, context);
		System.out.println(val);		
		
		/*
		if( context.variableExists(_str) )
			System.out.println(context.getVariable(_str).toString());
		else
			System.out.println(_str);
		*/
		
		return RunResults.Success;
	}
}

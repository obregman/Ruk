package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.ParsingHelper;
import core.Context;
import core.RegexDic;
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
		return RegexDic.if_d;
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
		
		Value varL = null;
		Value varR = null;
		
		if( context.variableExists(_varL) )
			varL = context.getVariable(_varL);
		else
			if( ParsingHelper.isNumeric(_varL) )
				varL = new Value(Integer.parseInt(_varL) );
			else
				varL = new Value(_varL);
		
		if( context.variableExists(_varR) )
			varR = context.getVariable(_varR);
		else
			if( ParsingHelper.isNumeric(_varR) )
				varR = new Value(Integer.parseInt(_varR) );
			else
				varR = new Value(_varR);
			
		
		if( ( _condition.equals("==") && varL.equals(varR) )|| 
			( _condition.equals("<") && varL.smallerThan(varR) ) ||
			( _condition.equals(">") && varR.smallerThan(varL) ) ) {
			
			for(FunctionBase function:_functions)
				function.run(context);
		}
		
		return RunResults.Success;
	}
}

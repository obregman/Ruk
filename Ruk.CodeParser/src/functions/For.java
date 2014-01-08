package functions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.ParsingHelper;
import core.Context;
import core.RegexDic;
import core.Value;

public class For extends FunctionBlock {
	
	String _var;
	String _from;
	String _to;
	
	public For() {
		
	}
	
	public FunctionBase createObj() {
		return new For();
	}
	
	@Override
	public String getDetector() {
		return RegexDic.for_d;
	}
	
	@Override
	public boolean evaluate() {
		
		Pattern p = Pattern.compile(getDetector());
		Matcher m = p.matcher(_code);
		
		if( m.matches() ) {
			_var = m.group(1);
			_from = m.group(2);
			_to = m.group(3);
		}
		else
			return false;
		
		return true;
	}
	
	@Override
	public RunResults run(Context context) {
		
		long from = 0;
		long to = 0;
		
		if( context.variableExists(_from) ) {
			String sFrom = context.getVariable(_from).toString();
			if( !ParsingHelper.isNumeric(sFrom) ) {
				context.addError("Invalid variable");
				return RunResults.Fail;
			}
			else
				from = Integer.parseInt(sFrom);
		}
		else
			from = Integer.parseInt(_from);
		
		context.updateVariable(_var, new Value(from));
		
		if( context.variableExists(_to) ) {
			String sTo = context.getVariable(_to).toString();
			if( !ParsingHelper.isNumeric(sTo) ) {
				context.addError("Invalid variable");
				return RunResults.Fail;
			}
			else
				to = Integer.parseInt(sTo);
		}
		else
			to = Integer.parseInt(_to);

		
		while(true) {
			
			long iter = context.getVariable(_var).val();
			
			if( iter > to )
				break;
			
			for(FunctionBase function:_functions)
				function.run(context);
			
			if(iter < to)
				context.updateVariable(_var, new Value(++iter));
			else
				if(iter > to)
					context.updateVariable(_var, new Value(--iter));
				else 
					break;
		}
		
		return RunResults.Success;
	}
}

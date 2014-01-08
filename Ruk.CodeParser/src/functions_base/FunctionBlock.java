package functions_base;

import java.util.ArrayList;
import java.util.List;

import core.Context;
import functions.End;

public class FunctionBlock extends FunctionBase {

	protected List<FunctionBase> _functions = new ArrayList<FunctionBase>();
	
	public void addFunctions(FunctionBase function) {
		_functions.add(function);
	}
	
	public RunResults run(Context context) {
		for(FunctionBase function:_functions) {
			function.run(context);
		}
		return RunResults.Success;
	}
	
	@Override
	public boolean insert(FunctionBase function) {
		
		if( _functions.size() > 0 && _functions.get(_functions.size()-1).insert(function))
			return true;
		
		if( function instanceof End )			
			return false;
		else
		{
			_functions.add(function);
			return true;
		}
	}
}

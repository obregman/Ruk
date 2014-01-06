package functions;

import java.util.ArrayList;
import java.util.List;

import core.Context;

public class FunctionBlock extends FunctionBase {

	List<FunctionBase> _functions = new ArrayList<FunctionBase>();
	
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
		if( function instanceof End )			
			return false;
		else
		{
			_functions.add(function);
			return true;
		}
	}
}

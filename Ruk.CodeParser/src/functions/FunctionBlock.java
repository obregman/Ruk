package functions;

import java.util.ArrayList;
import java.util.List;

import core.Context;

public class FunctionBlock {

	List<FunctionBase> _functions = new ArrayList<FunctionBase>();
	
	public void addFunctions(FunctionBase function) {
		_functions.add(function);
	}
	
	public void run(Context context) {
		for(FunctionBase function:_functions) {
			function.run(context);
		}
	}
}

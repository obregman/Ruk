package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.CodingRules;
import core.Context;
import core.Value;

public class End extends FunctionBase {
	
	public End() {
		
	}
	
	public FunctionBase createObj() {
		return new End();
	}
	
	@Override
	public String getDetector() {
		return ".*end.*";
	}
	
	@Override
	public boolean evaluate() {
		
		return true;
	}
	
	@Override
	public void run(Context context) {
		
	}
}

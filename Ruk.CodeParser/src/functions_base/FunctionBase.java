package functions_base;

import java.util.List;

import core.Context;

public class FunctionBase {
	
	protected int _lineNum;
	protected String _code;
	
	public String getDetector() {
		return "";
	}
	
	public void setCode(String code, int lineNum) {
		_lineNum = lineNum;
		_code = code;
	}
	
	public boolean evaluate() {
		return false;
	}
	
	public RunResults run(Context context) {
		return RunResults.Fail;
	}
	
	public FunctionBase createObj() {
		return null;
	}
	
	public boolean insert(FunctionBase function) {
		return false;
	}
}

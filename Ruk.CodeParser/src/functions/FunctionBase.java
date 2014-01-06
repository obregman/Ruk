package functions;

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
	
	public void run(Context context) {
		
	}
	
	public FunctionBase createObj() {
		return null;
	}
}

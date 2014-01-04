package scripts;

import scriptParsers.ScriptTree;

public class Script {
	
	protected String _name;
	protected String _type;
	
	public Script() {
		
	}
	
	public Script(String type) {
		_type = type;
	}
	
	public String getType() {
		return _type;		
	}
	
	public String getName() {
		return _name;
	}
	
	public ScriptRunResult run() {
		return null;
	}
	
	public String dump() {
		return "";
	}

	public Script getObject() {
		return null;
	}
	
	public boolean parse(ScriptTree tree) {
		return false;
	}

}

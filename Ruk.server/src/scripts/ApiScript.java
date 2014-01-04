package scripts;

import java.util.ArrayList;
import java.util.List;

import scriptParsers.ScriptBlock;
import scriptParsers.ScriptTree;
import scripts.ApiScript;

public class ApiScript extends Script {
	
	List<String> _inputParameters = new ArrayList<String>();
	String _do;
	String _return;
	
	public ApiScript() {
		super("api");
	}
	
	@Override
	public boolean parse(ScriptTree tree) {
		
		_name = tree.getName();
		
		ScriptBlock inputBlock = tree.getRoot().findBlock("input");
		if( inputBlock == null )
			return false;
		
		if( inputBlock != null ) {
			String[] params = inputBlock.innerElements.get(0).innerText.split(",");
			for (int i = 0; i < params.length; i++) {
				_inputParameters.add(params[i]);
			}
		}
		
		ScriptBlock doBlock = tree.getRoot().findBlock("do");
		if( doBlock == null )
			return false;
		
		_do = doBlock.innerText;
		
		ScriptBlock returnBlock = tree.getRoot().findBlock("return");
		if( returnBlock == null )
			return false;
		
		_return = returnBlock.innerText;
		
		return true;
	}
	
	@Override
	public ScriptRunResult run() {
		return ScriptRunResult.succeeded("{'foo':'bar'}");
	}
	
	@Override
	public Script getObject() {
		return new ApiScript();
	}
	
	@Override
	public String dump() {
		return _inputParameters.toString();
	}
	
	public void clean() {
		_inputParameters.clear();
	}

}

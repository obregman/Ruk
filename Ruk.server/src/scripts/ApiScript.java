package scripts;

import java.util.ArrayList;
import java.util.List;

import scriptParsers.ScriptBlock;
import scriptParsers.ScriptTree;
import scripts.ApiScript;

public class ApiScript extends Script {
	
	List<String> _inputParameters = new ArrayList<String>();
	
	public ApiScript() {
		super("api");
	}
	
	@Override
	public boolean parse(ScriptTree tree) {
		
		_name = tree.getName();
		
		ScriptBlock inputBlock = tree.getRoot().findBlock("input");
		if( inputBlock != null ) {
			String[] params = inputBlock.innerBlocks.get(0).text.split(",");
			for (int i = 0; i < params.length; i++) {
				_inputParameters.add(params[i]);
			}
		}
		
		return false;
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

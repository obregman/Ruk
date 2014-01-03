package scriptParsers;

import java.util.ArrayList;
import java.util.List;

import scripts.ApiScript;
import scripts.Script;

public class ApiScript {
	
	String _name;
	List<String> _inputParameters = new ArrayList<String>();
	
	public ApiScript() {
	}
	
	public boolean parse(ScriptTree tree) {
		
		ScriptBlock block = tree.findBlock("api");
		
		if( block == null )
			return false;
		
		ApiScript scriptObj = new ApiScript();
		
		scriptObj._name = block.text.split(" ")[1];
		
		ScriptBlock inputBlock = tree.findBlock("input");
		if( inputBlock != null ) {
			String[] params = inputBlock.innerBlocks.get(0).text.split(",");
			for (int i = 0; i < params.length; i++) {
				_inputParameters.add(params[i]);
			}
		}
		
		return false;
	}
	
	public String dump() {
		return _inputParameters.toString();
	}
	
	public void clean() {
		_inputParameters.clear();
	}

}

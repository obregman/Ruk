package scriptParsers;

import java.util.ArrayList;
import java.util.List;

public class ApiScript extends Script {
	
	List<String> _inputParameters = new ArrayList<String>();
	
	public ApiScript() {
	}
	
	@Override
	public boolean parse(String script) {
		
		boolean success = generate(script);
		if( !success )
			return false;
		
		ScriptBlock block = findBlock("api");
		
		if( block == null )
			return false;
		
		_name = block.text.split(" ")[1];
		
		ScriptBlock inputBlock = findBlock("input");
		if( inputBlock != null ) {
			String[] params = inputBlock.innerBlocks.get(0).text.split(",");
			for (int i = 0; i < params.length; i++) {
				_inputParameters.add(params[i]);
			}
		}
		
		return true;
	}
	
	public String dump() {
		return _inputParameters.toString();
	}
	
	public void clean() {
		_inputParameters.clear();
	}

}

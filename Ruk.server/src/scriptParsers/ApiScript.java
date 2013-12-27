package scriptParsers;

import java.util.ArrayList;
import java.util.List;

public class ApiScript extends ScriptParser {
	
	List<String> _inputParameters = new ArrayList<String>();
	
	@Override
	public boolean parse(String script) {
		
		boolean isApiScript = (script.indexOf("api") == 0);
		
		if( !isApiScript )
			return false;
		
		// input clause
		String inputText = getClause(script, "input");
		_inputParameters = commaSeparatedValuesToList(inputText);
		
		return true;
	}
	
	@Override
	public void run() {
		
	}
	
	public String dump() {
		return _inputParameters.toString();
	}

}

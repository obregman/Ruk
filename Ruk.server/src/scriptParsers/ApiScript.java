package scriptParsers;

import java.util.ArrayList;
import java.util.List;

import scriptParsers.Script.TextItem;

public class ApiScript extends Script {
	
	List<String> _inputParameters = new ArrayList<String>();
	
	public ApiScript() {
	}
	
	@Override
	public boolean parse(String script) {
		
		ScriptTree tree = ScriptTree.generate(script);
		
		/*
		
		String key = extractKeyword(script);
		if( key.equals("api") ) {
			return false;
		}

		_name = extractName(script);
		
		//boolean isApiScript = (script.indexOf("api") == 0);		
		//if( !isApiScript )
			//return false;
		
		// input clause
		String inputText = getClause(script, "input");
		_inputParameters = commaSeparatedValuesToList(inputText);
		*/
		
		return true;
	}
	
	@Override
	public void run() {
		
	}
	
	public String dump() {
		return _inputParameters.toString();
	}
	
	public void clean() {
		_inputParameters.clear();
		_name = "";
	}

}

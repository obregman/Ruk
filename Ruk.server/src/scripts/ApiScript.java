package scripts;

import java.util.ArrayList;
import java.util.List;

import core.CodeParser;
import core.Context;
import core.ParsedCode;
import scriptParsers.ScriptBlock;
import scriptParsers.ScriptTree;
import scripts.ApiScript;

public class ApiScript extends Script {
	
	List<String> _inputParameters = new ArrayList<String>();
	ParsedCode _do;
	
	public ApiScript() {
		super("api");
	}
	
	@Override
	public boolean parse(ScriptTree tree) {
		
		CodeParser parser = new CodeParser();
		
		_name = tree.getName();
		
		
		ScriptBlock inputBlock = tree.getRoot().findBlock("input");
		if( inputBlock == null )
			return false;
		
		if( inputBlock != null && inputBlock.innerElements.size() > 0 && inputBlock.innerElements.get(0).innerText != null) {
			String[] params = inputBlock.innerElements.get(0).innerText.split(",");
			for (int i = 0; i < params.length; i++) {
				_inputParameters.add(params[i]);
			}
		}
		
		ScriptBlock doBlock = tree.getRoot().findBlock("do");
		if( doBlock == null )
			return false;
		
		String doCode = doBlock.innerText;
		_do = parser.parse(doCode);
		if( _do.parsingFailed() )
			return false;
		
		return true;
	}
	
	@Override
	public ScriptRunResult run() {
		CodeParser parser = new CodeParser();
		// Run do
		Context doResult = parser.execute(_do);
		if( doResult.errorState() ) {
			if( doResult.getErrors().size() > 0 )
				return ScriptRunResult.failed(doResult.getErrors().get(0));
			else
				return ScriptRunResult.failed("Unspecified error");
		}
		else
			return ScriptRunResult.succeeded(doResult.getReturnValue().toString());
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

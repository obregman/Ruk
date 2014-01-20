package scripts;

import java.util.ArrayList;
import java.util.List;

import core.CodeParser;
import core.Context;
import core.ParsedCode;
import scriptParsers.ScriptBlock;
import scriptParsers.ScriptTree;
import scripts.ImmediateScript;

public class ImmediateScript extends Script {
	
	List<String> _inputParameters = new ArrayList<String>();
	ParsedCode _do;
	
	public ImmediateScript() {
		super("immediate");
	}
	
	@Override
	public boolean parse(ScriptTree tree) {
		
		CodeParser parser = new CodeParser();
		
		_name = tree.getName();
		
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
			String error = "N/A";
			if( doResult.getErrors().size() > 0 )
				error = doResult.getErrors().get(0);
			return ScriptRunResult.failed(error);
		}
		else
			return ScriptRunResult.succeeded(doResult.getReturnValue().toString());
	}
	
	@Override
	public Script getObject() {
		return new ImmediateScript();
	}
	
	@Override
	public String dump() {
		return _inputParameters.toString();
	}
	
	public void clean() {
		_inputParameters.clear();
	}

}

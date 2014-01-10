package scripts;

import java.util.ArrayList;
import java.util.List;

import scriptParsers.ScriptBlock;
import scriptParsers.ScriptTree;
import scripts.JobScript;

public class JobScript extends Script {
	
	List<String> _inputParameters = new ArrayList<String>();
	
	public JobScript() {
		super("job");
	}
	
	@Override
	public boolean parse(ScriptTree tree) {
		
		_name = tree.getName();
		
		ScriptBlock inputBlock = tree.getRoot().findBlock("input");
		if( inputBlock != null ) {
			String[] params = ((ScriptBlock)inputBlock.innerElements.get(0)).prefix.split(",");
			for (int i = 0; i < params.length; i++) {
				_inputParameters.add(params[i]);
			}
		}
		
		return true;
	}
	
	@Override
	public Script getObject() {
		return new JobScript();
	}
	
	@Override
	public String dump() {
		return _inputParameters.toString();
	}
	
	public void clean() {
		_inputParameters.clear();
	}

}

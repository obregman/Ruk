package scriptParsers;

import java.util.ArrayList;
import java.util.List;

public class ScriptBlock {
		
	public String title;
	public String innerText;
	public int start;
	public int end;
	public List<String> elements = new ArrayList<String>();
	public List<ScriptBlock> innerBlocks = new ArrayList<ScriptBlock>();
	public List<ScriptLine> lines = new ArrayList<ScriptLine>();
	
	
	public ScriptBlock findBlock(String str) {
		
		if ( title.contains(str) )
			return this;
		ScriptBlock foundBlock = null;
		for(ScriptBlock innerBlock:innerBlocks) {
			foundBlock = innerBlock.findBlock(str);
			if(foundBlock != null)
				break;
		}
		return foundBlock;
	}
	
}

package scriptParsers;

import java.util.ArrayList;
import java.util.List;

public class ScriptBlock {
		
	public String text;
	public int start;
	public int end;
	public List<String> elements = new ArrayList<String>();
	public List<ScriptBlock> innerBlocks = new ArrayList<ScriptBlock>();
	public List<ScriptLine> lines = new ArrayList<ScriptLine>();
	
	
	public ScriptBlock findBlock(String str) {
		
		if ( text.contains(str) )
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

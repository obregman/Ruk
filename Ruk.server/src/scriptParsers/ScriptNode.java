package scriptParsers;

import java.util.ArrayList;
import java.util.List;

public class ScriptNode {
	
	public int start;
	public int end;
	public List<String> elements = new ArrayList<String>();
	public List<ScriptNode> children = new ArrayList<ScriptNode>();	
	
}

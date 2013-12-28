package scriptParsers;

public class ScriptTree {
	
	ScriptNode _root;
	
	public ScriptTree() {
	}
	
	public ScriptNode getRoot() {
		return _root;
	}
	
	public static ScriptTree generate(String script) {
		script = cleanScript(script);
		
		ScriptTree tree = new ScriptTree();
		tree._root = generateSubNode(script,0,"");
		return tree;
	}
	
	private static ScriptNode generateSubNode(String script, int from, String endBy) {
		
		ScriptNode node = new ScriptNode();
		node.start = from;
		
		int elementStart = 0;
		boolean reachedEndOfBlock = false;
		for(int pos = from; pos < script.length(); pos++) {
			
			reachedEndOfBlock = false;
			
			char ch = script.charAt(pos);			
			
			if( ch == '\r' && pos + 1 < script.length() && script.charAt(pos + 1) == '\n') {
				// New line
				if( endBy.equals("\r\n") ) {
					node.text = script.substring(from, pos);
					node.end = pos;
					break;
				}
				ScriptNode childNode = generateSubNode(script, pos + 2, "\r\n");
				node.children.add(childNode);
				pos = childNode.end;
			}
			
			if( ch == '{' ) {
				node.text = script.substring(from, pos);
				ScriptNode childNode = generateSubNode(script, pos + 1, "}");
				node.children.add(childNode);
				pos = childNode.end;
			}
			
			if( ch == '}' ) {
				node.end = pos;
				break;		
			}
		}
		
		return node;
	}
	
	private static String cleanScript(String script) {
		StringBuilder sb = new StringBuilder();
		for(int pos = 0; pos < script.length(); pos++) {
			char ch = script.charAt(pos);
			if( ch != '\t' )
				sb.append(ch);				
		}
		return sb.toString();
	}
}

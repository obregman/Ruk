package scriptParsers;

public class ScriptTree {
	
	ScriptNode _root;
	
	public ScriptTree() {
	}
	
	public ScriptNode getRoot() {
		return _root;
	}
	
	public static ScriptTree generate(String script) {
		ScriptTree tree = new ScriptTree();
		tree._root = generateSubNode(script,0);
		return tree;
	}
	
	private static ScriptNode generateSubNode(String script, int from) {
		
		ScriptNode node = new ScriptNode();
		node.start = from;
		
		int elementStart = 0;
		boolean keepElement = false;
		boolean reachedEndOfBlock = false;
		for(int pos = from; pos < script.length(); pos++) {
			
			keepElement = false;
			reachedEndOfBlock = false;
			
			char ch = script.charAt(pos);
			
			if( ch == ' ' ) {
				keepElement = true;				
			}
			
			if( ch == '\n' ) {
				keepElement = true;
			}
			
			if( ch == '{' ) {
				keepElement = true;
				ScriptNode childNode = generateSubNode(script, pos + 1);
				node.children.add(childNode);
			}
			
			if( ch == '}' ) {
				keepElement = true;
				reachedEndOfBlock = true;		
			}
			
			if( keepElement ) {
				String element = script.substring(elementStart, pos);
				node.elements.add(element);
				elementStart = pos + 1;
			}
			
			if( reachedEndOfBlock ) {
				node.end = pos;
				break;
			}
		}
		
		return node;
	}
}

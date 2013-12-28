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
		for(int pos = from; pos < script.length(); pos++) {
			
			char ch = script.charAt(pos);
			
			if( ch == ' ' ) {
				String element = script.substring(elementStart, pos - 1);
				node.elements.add(element);
				elementStart = pos + 1;
			}
			
			if( ch == '\n' ) {
				
			}
			
			if( ch == '{' ) {
				ScriptNode childNode = generateSubNode(script, pos);
				node.children.add(childNode);
			}
			
			if( ch == '}' ) {
				node.end = pos;
				break;
			}
		}
		
		return node;
	}
}

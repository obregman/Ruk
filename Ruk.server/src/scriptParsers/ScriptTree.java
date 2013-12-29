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
		tree._root = generateSubNode(script,0);
		return tree;
	}
	
	private static ScriptNode generateSubNode(String script, int from) {
		
		boolean isBlock = false;
		boolean hasChildNode = false;
		if( script.charAt(from) == '{' ) { 
			isBlock = true;
			from++;			
		}
		
		ScriptNode node = new ScriptNode();
		node.start = from;
		
		for(int pos = from; pos < script.length(); pos++) {
			
			char ch = script.charAt(pos);			
			
			if( ch == ';' ) {
				// New line
				if( !isBlock ) {	
					
					if( pos != from ) {
						node.text = script.substring(from, pos).trim();
						node.end = pos;
					}
					else
						node = null;
					break;
				}
				ScriptNode childNode = generateSubNode(script, pos+1);
				if( childNode != null ) {
					node.children.add(childNode);
					pos = childNode.end + 1;					
				}				
			}
			else
			if( ch == '{' ) {
				hasChildNode = true;
				node.text = script.substring(from, pos).trim();				
				ScriptNode childNode = generateSubNode(script, pos);
				if( childNode != null ) {
					node.children.add(childNode);
					pos = childNode.end + 1;
				}
			}
			else
			if( ch == '}') {
				if( !hasChildNode )
					node.text = script.substring(from, pos).trim();				
				node.end = pos;
				break;		
			}
		}
		
		return node;
	}
	
	private static String cleanScript(String script) {
		StringBuilder sb = new StringBuilder();
		for(int pos = 0; pos < script.length(); pos++) {
			
			char ch1 = script.charAt(pos);
			char ch2 = ( pos+1<script.length() )?script.charAt(pos):0;
			
			if( ch1 == '\n' )
				sb.append(';');
			else
			if( ch1 == '\r' && ch2 == '\n') {
				sb.append(';');
				pos++;
			}
			else
				if( ch1 != '\t' )
					sb.append(ch1);
			
		}
		return sb.toString();
	}
}


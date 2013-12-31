package scriptParsers;

public class Script {
	
	ScriptBlock _root;
	protected String _name;
	
	public Script() {
	}
	
	public ScriptBlock getRoot() {
		return _root;
	}
	
	public String getName() {
		return _name;
	}
	
	public boolean parse(String script) {
		return false;
	}
	
	public boolean generate(String script) {
		script = cleanScript(script);
		
		_root = breakToBlocks(script,0);
		
		if (_root == null)
			return false;
		else
			return true;
	}
	
	private static ScriptBlock breakToBlocks(String script, int from) {
		
		ScriptBlock node = new ScriptBlock();
		boolean hasBlock = false;
		int lineStart = from;		
		node.start = from;
		node.end = script.length();
		
		for(int pos = from; pos < script.length(); pos++) {
			
			char ch = script.charAt(pos);
			
			if( ch == ';' ) {
				
				if (lineStart == pos ) {
					lineStart++;
					continue;
				}
					
				ScriptLine line = new ScriptLine(lineStart, pos - 1, script.substring(lineStart, pos));
				node.lines.add(line);
				lineStart = pos + 1;
			}
			else
			if( ch == '{' ) {
				hasBlock = true;
				node.text = script.substring(lineStart, pos - 1);
				ScriptBlock innerBlock = breakToBlocks(script, pos + 1);
				node.innerBlocks.add(innerBlock);
				pos = innerBlock.end;
			}
			else
				if( ch == '}' ) {
					node.end = pos - 1;
					break;
				}			
		}
		
		if( !hasBlock )
			node.text = script.substring(node.start, node.end);
		
		
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
	
	protected ScriptBlock findBlock(String str) {
		return _root.findBlock(str);
	}
}


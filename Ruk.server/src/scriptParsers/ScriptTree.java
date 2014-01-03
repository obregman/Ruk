package scriptParsers;

import scripts.Script;

public class ScriptTree {
	
	ScriptBlock _root;
	String _name;
	String _type;
	
	public ScriptTree(ScriptBlock root) {
		_root = root;
	}
	
	public ScriptBlock getRoot() {
		return _root;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getType() {
		return _type;
	}
	
	public Script parse(String script) {
		return null;
	}
		
	public static ScriptTree buildTree(String script) {
		script = cleanScript(script);
		ScriptBlock root = breakToBlocks(script, 0);
		if( root == null )
			return null;
		
		ScriptTree tree = new ScriptTree(root);	
		
		// Script type
		String[] parts = root.title.split(" ");
		if( parts.length > 0)
			tree._type = parts[0];
		if( parts.length > 1)
			tree._name = parts[1];		
		
		return tree;
	}
	
	private static ScriptBlock breakToBlocks(String script, int from) {
		
		ScriptBlock block = new ScriptBlock();
		boolean hasBlock = false;
		int lineStart = from;		
		block.start = from;
		block.end = script.length();
		
		for(int pos = from; pos < script.length(); pos++) {
			
			char ch = script.charAt(pos);
			
			if( ch == ';' ) {
				
				if (lineStart == pos ) {
					lineStart++;
					continue;
				}
					
				ScriptLine line = new ScriptLine(lineStart, pos - 1, script.substring(lineStart, pos));
				block.lines.add(line);
				lineStart = pos + 1;
			}
			else
			if( ch == '{' ) {
				hasBlock = true;
				block.title = script.substring(lineStart, pos - 1);
				ScriptBlock innerBlock = breakToBlocks(script, pos + 1);
				block.innerBlocks.add(innerBlock);
				pos = innerBlock.end;
			}
			else
				if( ch == '}' ) {
					block.innerText = script.substring(from, pos - 1);
					block.end = pos - 1;
					break;
				}			
		}
		
		if( !hasBlock )
			block.innerText = script.substring(block.start, block.end);
		
			
		return block;
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


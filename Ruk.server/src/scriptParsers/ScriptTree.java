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
		ScriptBlock root = parse(script, 0);
		if( root == null )
			return null;
		
		ScriptTree tree = new ScriptTree(root);	
		
		// Script type
		String[] parts = ((ScriptBlock)root.innerElements.get(0)).prefix.split(" ");
		if( parts.length > 0)
			tree._type = parts[0];
		if( parts.length > 1)
			tree._name = parts[1];		
		
		return tree;
	}
	
public static ScriptBlock parse(String script, int from) {
		
		ScriptBlock block = new ScriptBlock();
		
		int lineStart = from;
		boolean contentReached = false;
		
		int pos = from;
		while(pos < script.length()) {
			
			char ch = script.charAt(pos);
			
			if( ch == '\t' )
				continue;
			else
			if( ch == '\r' || ch == '\n' ) {
				// End of line
				if(contentReached) {
					ScriptElement lineElement = new ScriptElement();
					lineElement.start = lineStart;
					lineElement.end = pos - 1;
					lineElement.innerText = script.substring(lineElement.start, pos);
					block.innerElements.add(lineElement);
				}
				pos = TextHelper.nextRealCharacter(script, pos);
				if( pos < 0 )
					break;
				lineStart = pos;
				continue;
			}
			else
			if( ch == '{' ) {
				// Open block
				ScriptBlock innerBlock = parse(script, pos + 1);
				innerBlock.prefix = script.substring(lineStart, pos - 1);
				block.innerElements.add(innerBlock);
				pos = TextHelper.nextRealCharacter(script, innerBlock.end + 2);
				if( pos < 0 )
					break;
				lineStart = pos;
				continue;
			}
			else
			if( ch == '}') {
				block.start = from;
				block.end = pos - 1;
				break;
			}
			else
				contentReached = true;
			
			pos++;
		}
		
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


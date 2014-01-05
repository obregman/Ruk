package scriptParsers;

public class ScriptBlock extends ScriptElement {

	public String prefix;
	
	public ScriptBlock findBlock(String name) {
		
		if( prefix !=null && prefix.equals(name) )
			return this;
		
		for(ScriptElement element:innerElements) {
			if( element instanceof ScriptBlock) {
				ScriptBlock block = ((ScriptBlock)element).findBlock(name);
				if( block != null )
					return block;
			}
		}
		return null;
	}
}

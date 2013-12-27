package scriptParsers;

import java.util.ArrayList;
import java.util.List;

public class ScriptParser {
	
	public boolean parse(String script) {
		return false;
	}
	
	public void run() {
		
	}
	
	protected String getClause(String script, String keyword) {
		
		String text = "";
		int keywordPos = script.indexOf(keyword);
		
		if( found(keywordPos) ) {
			int open = script.indexOf("{", keywordPos);
			if( found(open) ) {
				int close = script.indexOf("}", open);
				if( found(close) ) {
					text = script.substring(open + 1, close - 1);				
				}					
			}
		}
		
		return text;
	}
	
	protected boolean found(int pos) {
		return pos >= 0;
	}
	
	protected List<String> commaSeparatedValuesToList(String text) {
		
		List<String> list = new ArrayList<String>();
		String[] tokens = text.split(",");
		for(String str:tokens)
			list.add(str);
		return list;			
	}

}

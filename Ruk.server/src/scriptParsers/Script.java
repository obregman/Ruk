package scriptParsers;

import java.util.ArrayList;
import java.util.List;

public class Script {
	
	protected String _name;
	
	protected class TextItem {
		public TextItem(String str, int pos) { this.str = str; this.pos = pos; }
		public String str;
		public int pos;
	}
	
	public boolean parse(String script) {
		return false;
	}
	
	public void run() {
		
	}
	
	protected String extractKeyword(String text) {
		TextItem item;
		item = getNext(text, 0);
		if( item != null ) {
			String key = item.str;
			return key;
		}
		return "";
	}
	
	protected String extractName(String text) {
		TextItem item;
		item = getNext(text, 0);
		if( item != null ) {
			item = getNext(text, item.pos);
			String name = item.str;
			return name;
		}
		return "";
	}
	
	public String getName() {
		return _name;
	}
	
	protected TextItem getNext(String text, int curPos) {
		TextItem next = null;
		for(int pos = curPos; pos < text.length(); pos++) {
			char ch = text.charAt(pos);
			if( ch == ' ' )
				next = new TextItem(text.substring(curPos, pos - 1), pos);
			else
				if( ch == '{')
					next = new TextItem("{", pos);
				else
					if( ch == '{')
						next = new TextItem("}", pos);
		}		
		
		return next;		
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

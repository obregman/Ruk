package scriptParsers;
import java.util.ArrayList;
import java.util.List;


public class ScriptElement {

	public List<ScriptElement> innerElements = new ArrayList<ScriptElement>();
	
	public String innerText;
	public int start;
	public int end;
	
	public ScriptElement get(int index) {
		if( index > innerElements.size() ) {
			return innerElements.get(index);
		}
		return null;
	}
}

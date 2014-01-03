package scriptParsers;
import java.util.ArrayList;
import java.util.List;


public class ScriptElement {

	public List<ScriptElement> innerElements = new ArrayList<ScriptElement>();
	
	public String innerText;
	public int start;
	public int end;
}

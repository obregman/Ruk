package scriptParsers;

public class ScriptLine {

	ScriptLine(int start, int end, String text) {
		this.start = start;
		this.end = end;
		this.text = text;
	}
	
	public String text;
	public int start;
	public int end;
}

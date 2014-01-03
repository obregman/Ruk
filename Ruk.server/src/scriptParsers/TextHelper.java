package scriptParsers;

public class TextHelper {

	public static int nextRealCharacter(String text, int from) {
		int pos = from;
		while(pos < text.length()) {
			char ch = text.charAt(pos);
			if( ch != ' ' && ch != '\r' && ch != '\n' && ch != '\t' )
				return pos;
			pos++;
		}
		return -1;
	}
}

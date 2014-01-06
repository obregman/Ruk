package core;

public class CodingRules {

	public static boolean isNumeric(String str) {
		if( str.matches("[0-9]+") )
			return true;
		else
			return false;
	}
	
	public static boolean canBeAVariable(String str) {
		if( str.matches("[A-z][A-z0-9]*") )
			return true;
		else
			return false;
	}
}

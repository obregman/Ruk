package core;

public class RegexDic {
	public static String quotedString = "[\"'](.+)[\"']";
	// Function detectors
	public static String print_d = "print (.+)";
	public static String for_d = "for ([A-z0-9]+) in ([A-z0-9]+)..([A-z0-9]+)";
	public static String if_d = "if ([A-z0-9]+) (==|!=|<|>) ([A-z0-9]+) then";
	public static String assign_d = "([A-z][A-z0-9 ]*)[=](.+)";
	public static String end_d = "end";
	public static String return_d = "return (.+)";
			
}

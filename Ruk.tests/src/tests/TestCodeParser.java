package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import core.CodeParser;
import core.Context;
import core.ParsedCode;

public class TestCodeParser {
	
	@Test
	public void testReturn() {
		
		String code = "return 10";
		CodeParser parser = new CodeParser();
		ParsedCode parsedCode = parser.parse(code);

		if( parsedCode == null || parsedCode.codeTree() == null )
			fail(String.format("Failed to parse '%s'", code));
		
		Context context = parser.execute(parsedCode);
		
		if( context.errorState() )
			fail(String.format("'%s' execution failed", code));
		
		if( context.getReturnValue() == null || context.getReturnValue().val() != 10 )
			fail(String.format("Wrong return value for '%s'", code));
	}
	
	@Test
	public void testInvalidCode() {
		String code = "foofoofoo";
		CodeParser parser = new CodeParser();
		ParsedCode parsedCode = parser.parse(code);
		
		if( !parsedCode.parsingFailed() )
			fail(String.format("Parsing not reported as failed"));
	}
}

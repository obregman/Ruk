import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import scriptParsers.ApiScript;


public class TestScripts {

	@Test
	public void testApiScript() {

		String filename = "..\\Ruk.server\\scriptSamples\\api-mult.ruk";
		String content = null;
	    File file = new File(filename);
	    try {
	        FileReader reader = new FileReader(file);
	        char[] chars = new char[(int) file.length()];
	        reader.read(chars);
	        content = new String(chars);
	        reader.close();
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
		
		ApiScript apiScript = new ApiScript();
		
		if( !apiScript.parse(content) ) {
			fail("Failed to parse script");			
		}		
	}

}

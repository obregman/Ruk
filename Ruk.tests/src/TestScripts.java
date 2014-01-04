import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;
import scriptParsers.ScriptTree;


public class TestScripts {
	
	@Test
	public void testScriptTreeCreation() {

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
		
		ScriptTree tree = ScriptTree.buildTree(content);
		
		if( tree.getRoot() == null ) {
			fail("Failed to generate tree");	
		}		
	}

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
		
	    ScriptTree tree = ScriptTree.buildTree(content);
	    
	    if( tree.getType().equals("api") ) {
	    	scripts.ApiScript apiScript = new scripts.ApiScript();
	    	boolean success = apiScript.parse(tree);
	    	
	    	if( !success ) {
				fail("Failed to parse api script");			
			}	
	    }
			
	}

}

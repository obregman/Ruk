package tests;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import core.TextFileReader;
import scriptParsers.ScriptTree;


public class TestScripts {
	
	@Test
	public void testScriptTreeCreation() {

		String content = TextFileReader.readFile("..\\Ruk.server\\scriptSamples\\api-mult.ruk");
		
		ScriptTree tree = ScriptTree.buildTree(content);
		
		if( tree.getRoot() == null ) {
			fail("Failed to generate tree");	
		}		
	}

	@Test
	public void testAddApiScript() {

		String content = TextFileReader.readFile("..\\Ruk.server\\scriptSamples\\api-mult.ruk");
		
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

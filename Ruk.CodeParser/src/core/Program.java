package core;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Program {

	public static void main(String[] args) {
		
		String filename = "samples/code";
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
	    
	    CodeParser parser = new CodeParser();
	    Context context = parser.parse(content);
	    
	    if (context.errorState()) {
	    	System.out.println("!!! Error !!!");
	    	for(String err:context._errors)
	    		System.out.println(err);
	    }
	}

}

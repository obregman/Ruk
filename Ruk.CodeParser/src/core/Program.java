package core;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Program {

	public static void main(String[] args) {
		
		String filename = "code";
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
	    parser.parse(content);
	}

}

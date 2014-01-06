package core;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

import functions.Assign;
import functions.End;
import functions.For;
import functions.FunctionBase;
import functions.If;
import functions.Print;
import functions.Return;


public class CodeParser {
	
	List<FunctionBase> _functions = new ArrayList<FunctionBase>();
	
	public CodeParser() {
		registerFunctions();
	}
	
	private void registerFunctions() {
		_functions.add(new Assign());
		_functions.add(new For());
		_functions.add(new If());
		_functions.add(new Print());
		_functions.add(new End());
		_functions.add(new Return());
	}
	
	public Context parse(String code) {
		
		List<FunctionBase> functionsList = new ArrayList<FunctionBase>();
		List<String> lines = splitToLines(code);

		for(String line:lines) {
			FunctionBase function = evaluateLine(line);
			if( function == null ) {
				// Error - TBD
				
			}
			else
				functionsList.add(function);
		}
		
		for(FunctionBase function:functionsList)
			function.evaluate();
		
		List<FunctionBase> codeTree = buildCodeTree(functionsList);
		
		Context context = runCode(codeTree);
		return context;
	}
	
	private List<String> splitToLines(String code) {
		
		List<String> lines = new ArrayList<String>();
		
		int mark = 0;
		int pos = 0;
		while(pos < code.length()) {
			char ch = code.charAt(pos);
			if( ch == '\r' || ch == '\n' || ch == '\t' ) {
				String line = code.substring(mark, pos);
				lines.add(line);
				pos = TextHelper.nextRealCharacter(code, pos);
				if( pos < 0 ) {
					lines.add(code.substring(mark, code.length()));
					break;
				}
				mark = pos;
			}
			else
				pos++;				
		}
		
		if( mark < pos ) {
			String line = code.substring(mark, pos);
			lines.add(line);
		}
		
		return lines;			
	}
	
	private FunctionBase evaluateLine(String lineOfCode) {
		
		boolean matchFound = false;
		FunctionBase function = null;
		
		int lineNum = 0;
		for(FunctionBase functionTempl:_functions) {
			String regex = functionTempl.getDetector();
			
			boolean match = detectorMatch(regex, lineOfCode);
			
			if( match ) {		
				System.out.println("Match [" + lineOfCode +"] to regex [" + regex + "]");
				function = functionTempl.createObj();
				function.setCode(lineOfCode, lineNum);
				lineNum++;
									
				matchFound = true;
				break;
			}
		}
		
		if(!matchFound)
			System.out.println("No match to line [" + lineOfCode + "]");
		
		return function;
	}
	
	private boolean detectorMatch(String regex, String lineOfCode) {
		
		boolean match = lineOfCode.matches(regex);
		
		return match;
	}
	
	private List<FunctionBase> buildCodeTree(List<FunctionBase> functions) {
		
		List<FunctionBase> codeTree = new ArrayList<FunctionBase>();
		
		Stack<FunctionBase> functionStack = new Stack<FunctionBase>();
		for(FunctionBase function:functions) {
			
			if( functionStack.size() > 0 ) {
				
				FunctionBase parent = functionStack.peek();
				
				if( !parent.insert(function) ) {
					codeTree.add(function);
					functionStack.pop();	
				}
			}
			else
			{
				codeTree.add(function);
				functionStack.add(function);
			}
		}
		
		return codeTree;
	}
	
	private Context runCode(List<FunctionBase> code) {
		
		System.out.println("----- Executing -----");
		
		Context context = new Context();
		
		for( FunctionBase function:code) {
			function.run(context);
			
			if( context.getErrors().size() > 0 ) {
				System.out.println("Errors:");
				for(String error:context.getErrors())
					System.out.println(error);
				break;
			}	
		}
		
		return context;
	}
}

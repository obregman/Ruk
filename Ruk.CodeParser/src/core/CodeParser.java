package core;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

import functions.Assign;
import functions.End;
import functions.For;
import functions.If;
import functions.Print;
import functions.Return;
import functions_base.FunctionBase;


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
		
		Context context = new Context();
		
		List<FunctionBase> functionsList = new ArrayList<FunctionBase>();
		List<String> lines = splitToLines(code);

		int lineNum = 1;
		for(String line:lines) {
			if( line.length() == 0 || line.startsWith("#") ) { 
				// Remark
				lineNum++;
				continue;
			}
			
			FunctionBase function = evaluateLine(context, lineNum, line);
			if( function == null || context.errorState() == true ) {
				return context;				
			}
			else
				functionsList.add(function);
			
			lineNum++;
		}
		
		List<FunctionBase> codeTree = buildCodeTree(functionsList);
		
		runCode(context, codeTree);
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
	
	private FunctionBase evaluateLine(Context context, int lineNum, String lineOfCode) {
		
		boolean matchFound = false;
		FunctionBase function = null;
		
		for(FunctionBase functionTempl:_functions) {
			String regex = functionTempl.getDetector();
			
			boolean match = detectorMatch(regex, lineOfCode);
			
			if( match ) {		
				System.out.println("Match [" + lineOfCode +"] to regex [" + regex + "]");
				function = functionTempl.createObj();
				function.setCode(lineOfCode, lineNum);
				boolean evaluationSucceeded = function.evaluate();
				if( !evaluationSucceeded ) {					
					break;
				}
					
				matchFound = true;
				break;
			}
		}
		
		if(!matchFound) {
			System.out.println("No match to line [" + lineOfCode + "]");
			context.setErrorState(true);
			context.addError(String.format("Line %d: cannot evaluate line [%s]", lineNum, lineOfCode));
		}
		
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
					functionStack.add(function);
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
	
	private Context runCode(Context context, List<FunctionBase> code) {
		
		System.out.println("----- Executing -----");
		
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

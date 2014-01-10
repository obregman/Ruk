package core;

import java.util.ArrayList;
import java.util.List;

import functions_base.FunctionBase;

public class ParsedCode {
	
	boolean _parsingFailed; 
	String _parsingError;
	List<FunctionBase> _codeTree;
	
	public void setParsingFailed(boolean parsingFailed) {
		_parsingFailed = parsingFailed;
	}
	
	public boolean parsingFailed() {
		return _parsingFailed;
	}
	
	public void setParsingError(String parsingError) {
		_parsingError = parsingError;
	}
	
	public String parsingError() {
		return _parsingError;
	}
	
	public void setCodeTree(List<FunctionBase> codeTree) {
		_codeTree = codeTree;
	}
	
	public List<FunctionBase> codeTree() {
		return _codeTree;
	}
}

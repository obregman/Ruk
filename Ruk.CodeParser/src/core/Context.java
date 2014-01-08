package core;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Context {

	public enum ResultTypes {
		Success,
		Error
	}
	
	ResultTypes _result;
	List<String> _errors = new ArrayList<String>();
	boolean _errorState = false;
	Hashtable<String, Value> _variables = new Hashtable<String, Value>();
	Value _return;
	
	public Context() {
	}
	
	public void updateVariable(String name, Value value) {
		_variables.put(name, value);
	}
	
	public boolean variableExists(String name) {
		return _variables.containsKey(name);
	}
	
	public Value getVariable(String name) {
		return _variables.get(name);
	}
	
	public void addError(String error) {
		_errors.add(error);
	}
	
	public List<String> getErrors() {
		return _errors;
	}
	
	public void setReturnValue(Value retVal) {
		_return = retVal;
	}
	
	public Value getReturnValue() {
		return _return;
	}
	
	public void setResult(ResultTypes result) {
		_result = result;
	}
	
	public ResultTypes result() {
		return _result;
	}
	
	public void setErrorState(boolean errorState) {
		_errorState = errorState;
	}
	
	public boolean errorState() {
		return _errorState;
	}
	
	
}

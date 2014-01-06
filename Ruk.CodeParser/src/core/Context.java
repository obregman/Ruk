package core;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Context {

	List<String> _errors = new ArrayList<String>();
	Hashtable<String, Value> _variables = new Hashtable<String, Value>();
	
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
}

package core;

public class Value {

	enum Types {
		Long,
		String
	}
	
	Types _type;
	long _lVal;
	String _sVal;

	public Value(long l) {
		_lVal = l;
		_type = Types.Long;
	}
	
	public Value(String s) {
		_sVal = s;
		_type = Types.String;
	}
	
	public void append(String s) {
		_sVal += s;
	}
	
	public long val() {
		return _lVal;
	}
	
	public String toString() {
		if( _type == Types.Long)
			return String.valueOf(_lVal);
		else
			return _sVal;
	}
}

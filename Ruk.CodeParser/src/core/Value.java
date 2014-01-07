package core;

public class Value {

	public enum Types {
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
	
	public Types type() {
		return _type;
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
	
	public boolean equals(Value other) {
		if( _type == Types.Long ) {
			return _lVal == other._lVal;
		}
		else
			return _sVal.equals(other._sVal);
	}
	
	public boolean smallerThan(Value other) {
		if( _type == Types.Long ) {
			return _lVal < other._lVal;
		}
		else
			return false;
	}
}

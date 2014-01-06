package core;

public class Value {

	long _lVal;

	public Value(long l) {
		_lVal = l;
	}
	
	public long val() {
		return _lVal;
	}
	
	public String toString() {
		return String.valueOf(_lVal);
	}
}

package core;

import java.util.List;

public class Value {

	public enum Types {
		Long,
		String,
		List
	}
	
	Types _type;
	long _lVal;
	String _sVal;
	List<Value> _list;

	public Value(long l) {
		_lVal = l;
		_type = Types.Long;
	}
	
	public Value(String s) {
		_sVal = s;
		_type = Types.String;
	}

	public Value(List<Value> list) {
		_list = list;
		_type = Types.List;
	}
	
	public Types type() {
		return _type;
	}
	
	public Value add(String s) {
		_sVal += s;
		return this;
	}
	
	public Value add(Value val) {
		if (_type != val._type)
			return this;
		
		if( _type == Types.String )
			_sVal += val._sVal;
		else
			if( _type == Types.Long )
				_lVal += val._lVal;
		return this;
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
	
	public static Value add(Value v1, Value v2) {
		if( v1._type == Types.String && v2._type == Types.String)
			return new Value(v1._sVal + v2._sVal);
		else
			if( v1._type == Types.Long && v2._type == Types.Long )
				return new Value(v1._lVal + v2._lVal);
			else
				if( v1._type == Types.String && v2._type == Types.Long )
					return new Value(v1._sVal + String.valueOf(v2._lVal));
				else
					if( v2._type == Types.String && v1._type == Types.Long )
						return new Value(String.valueOf(v1._lVal) + v2._sVal);
		return null;
	}
	
	public static Value subtract(Value v1, Value v2) {
		if (v1._type != v2._type)
			return null;
		
		if( v1._type == Types.String )
			return null;
		else
			if( v1._type == Types.Long )
				return new Value(v1._lVal - v2._lVal);
		return null;
	}
	
	public static Value mult(Value v1, Value v2) {
		if (v1._type != v2._type)
			return null;
		
		if( v1._type == Types.String )
			return null;
		else
			if( v1._type == Types.Long )
				return new Value(v1._lVal * v2._lVal);
		return null;
	}
	
	public static Value div(Value v1, Value v2) {
		if (v1._type != v2._type)
			return null;
		
		if( v1._type == Types.String )
			return null;
		else
			if( v1._type == Types.Long )
				return new Value(v1._lVal / v2._lVal);
		return null;
	}
}

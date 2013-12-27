package Mongodb;

import javax.naming.directory.InvalidAttributesException;

import JSON.JSONObject;

import com.mongodb.BasicDBObject;

public class MongoDBConfig {
	
	final String DEFAULT_COLLECTION = "config";
	
	String _collection = DEFAULT_COLLECTION;
	MongoDBAccess _mda;
	
	public MongoDBConfig(MongoDBAccess mda) {
		_mda = mda;
	}
	
	public MongoDBConfig(MongoDBAccess mda, String collection) {
		_mda = mda;
		_collection = collection;
	}
	
	public void setConfigParam(String key, String value) {
		
		// Check if the parameter exists
		String valueExists = _mda.query(_collection, new BasicDBObject("_id", key));
		if( valueExists.length() == 0 ) {
			// Value does not exist
			
			try {
			_mda.insert(_collection, new BasicDBObject("_id", key).append("value", value));
			} catch(InvalidAttributesException ex) {
				ex.printStackTrace();
			}
		}
		else
		{
			// Value exists - update
			try {
				_mda.update(_collection, new BasicDBObject("_id", key), new BasicDBObject("value", value));
				} catch(InvalidAttributesException ex) {
					ex.printStackTrace();
				}
		}
	}
	
	public String readConfigParam(String key) {
		
		String res = _mda.query(_collection, new BasicDBObject("_id", key));
		if( res.length() == 0 ) 
			return null;
		
		JSONObject json = new JSONObject(res);
		String value = json.getString("value");
		return value;
	}

}

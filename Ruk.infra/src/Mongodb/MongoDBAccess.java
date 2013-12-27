package Mongodb;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import java.net.UnknownHostException;
import java.util.Set;

import javax.naming.directory.InvalidAttributesException;

public class MongoDBAccess {
	
	String _host = "localhost";
	int _port = 27017;
	String _dbName = "";
	
	MongoClient _mongoClient;
	DB _db;
	
	public MongoDBAccess(String dbName) {
		_dbName = dbName;
	}

	public void init() {
		if( _db == null )
			_db = getDB();
	}
	
	public void close() {
		if( _mongoClient != null ) {
			_mongoClient.close();
			_db = null;
		}
	}
		
	/***
	 * Get DB object
	 * @return
	 */
	public DB getDB() {

		if( _db != null )
			return _db;
		
		DB db = null;
		
		try {
			
			_mongoClient = new MongoClient( _host, _port );
			
			db = _mongoClient.getDB( _dbName );
			
		}
		catch(UnknownHostException ex) {
			ex.printStackTrace();
		}
		
		return db;		
	}
	
	public Set<String> getCollections() {
		
		if( _db == null )
			_db = getDB();
		
		Set<String> colls = _db.getCollectionNames();
		return colls;		
	}
	
	/***
	 * Insert a document to a collection
	 * @param colName
	 * @param keys
	 * @param values
	 * @throws InvalidAttributesException
	 */
	public void insert(String colName, BasicDBObject dbObject) throws InvalidAttributesException {
		
		if( _db == null )
			_db = getDB();
		
		DBCollection collection = _db.getCollection(colName);
		collection.insert(dbObject);		
	}
	
	/***
	 * Insert array of documents to a collection
	 * @param colName
	 * @param dbObjectArr
	 * @throws InvalidAttributesException
	 */
	public void insert(String colName, BasicDBObject[] dbObjectArr) throws InvalidAttributesException {
		
		if( _db == null )
			_db = getDB();
		
		DBCollection collection = _db.getCollection(colName);
		collection.insert(dbObjectArr);		
	}
	
	/***
	 * Update a document
	 * @param colName
	 * @param where
	 * @param dbObject
	 * @throws InvalidAttributesException
	 */
	public void update(String colName, BasicDBObject where, BasicDBObject dbObject) throws InvalidAttributesException {
		
		if( _db == null )
			_db = getDB();
		
		DBCollection collection = _db.getCollection(colName);
		collection.update(where, dbObject);
	}
	
	/***
	 * Query collection by key/value. Returns JSON formatted string.
	 * @param colName
	 * @param key
	 * @param value
	 */
	public String query(String colName, BasicDBObject dbObject) {
		
		if( _db == null )
			_db = getDB();
		
		DBCollection collection = _db.getCollection(colName);
		DBCursor cursor = collection.find(dbObject);
		
		String result = "";
		while( cursor.hasNext() ) {
			if (result.length() != 0 )
				result += ",";
			 result += cursor.next().toString();
		}
		cursor.close();
		
		return result;
	
	}
	
	/***
	 * Delete a document by key/value
	 * @param colName
	 * @param key
	 * @param value
	 */
	public void delete(String colName, BasicDBObject dbObject) {
		
		if( _db == null )
			_db = getDB();
		
		DBCollection collection = _db.getCollection(colName);
		collection.remove(dbObject);
	}
	
	/***
	 * 
	 * @param colName
	 * @param attribName
	 */
	public void createIndex(String colName, String attribName) {
		
		if( _db == null )
			_db = getDB();
		
		DBCollection collection = _db.getCollection(colName);
		collection.ensureIndex(attribName);
	}
	
	/***
	 * 
	 * @param colName
	 */
	public void dropCollection(String colName) {
		
		if( _db == null )
			_db = getDB();
		
		DBCollection collection = _db.getCollection(colName);
		collection.drop();
	}

}

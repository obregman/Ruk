package Mongodb;

import java.util.ArrayList;
import java.util.List;

import JSON.JSONArray;

import com.mongodb.BasicDBObject;

public class MongoDBHelper {

	public static List<String> readList(String db, String collection, BasicDBObject query, String listElementName) {
		List<String> list = new ArrayList<String>();
		
		MongoDBAccess mda = new MongoDBAccess(db);
		String res = mda.query(collection, query);
		mda.close();
		res = "[" + res + "]";
		
		if( res.length() > 0 ) {
		
			JSONArray arr = new JSONArray(res);
			
			for(int i = 0; i < arr.length(); i++) {
				
				String value = arr.getJSONObject(i).getString(listElementName);
				list.add(value);
			}
		}
		
		return list;
	}
}

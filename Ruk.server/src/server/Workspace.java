package server;

import java.util.Hashtable;

import scripts.ApiScript;


public class Workspace {
		
	Server _server;
	
	Hashtable<String,ApiScript> _apis = new Hashtable<String, ApiScript>();
	
	public Workspace(Server server) {
		_server = server;
	}
	
	public void addApi(ApiScript script) {
		_apis.put(script.getName(), script);
	}
	
	public void removeApi(String name) {
		if( _apis.containsKey(name) )
			_apis.remove(name);
	}
	
	public boolean apiExists(String name) {
		return _apis.containsKey(name);
	}
}

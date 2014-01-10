package server;

import java.nio.channels.SocketChannel;

import REST.RESTService;

public class RESTServer extends RESTService {
	
	Server _server;
	
	public RESTServer(Server server) {
		_server = server;
	}
	
	protected void handleGet(SocketChannel channel, String uri, String data) {
		_server.handleCommand(channel, uri, data);
	}
	
	protected void handlePut(SocketChannel channel, String uri, String data) {
	}
	
	protected void handlePost(SocketChannel channel, String uri, String data) {
		_server.handleCommand(channel, uri, data);
	}
	
	protected void handleDelete(SocketChannel channel, String uri, String data) {
	}

}

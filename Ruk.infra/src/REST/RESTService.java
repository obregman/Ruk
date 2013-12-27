package REST;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import HTTP.HTTPRequest;
import Logger.LogWriter;
import Logger.LogWriter.LogLevel;

public class RESTService implements IDataHandler, Runnable {
	
	public static Charset CHARSET = Charset.forName("UTF-8");
	public static CharsetEncoder ENCODER = CHARSET.newEncoder();
	
	TCPServer _server;	
	
	public RESTService() {
	}
	
	public void startListener(int port) {
		
		_server = new TCPServer(port, this);
		Thread thread = new Thread(this);
		thread.start();
	}

	public void stopListener() {
		
		_server.quit();
	}
	
	public void run() {		
		  _server.run();
	}
	
	public void handleData(String request, SocketChannel channel) throws Exception {
		
		HTTPRequest req = new HTTPRequest(request);
		
		if( req.operation == null ) {
			
			throw new Exception("Invalid request format: " + request);
		}
		
		if( req.operation.equals("GET") ) {
			
			LogWriter.log(LogLevel.Debug, "GET request received (" + req.uri + ")");
			handleGet(channel, req.uri, req.data);
			
		}
		else
			if( req.operation.equals("PUT") ) {
				
				LogWriter.log(LogLevel.Debug, "PUT request received (" + req.uri + ")");
				handlePut(channel, req.uri, req.data);
				
			}
			else
				if ( req.operation.equals("POST") ) {
					LogWriter.log(LogLevel.Debug, "POST request received (" + req.uri + ")");
					handlePost(channel, req.uri, req.data);
				}
				else
					if( req.operation.equals("DELETE") ) {
						LogWriter.log(LogLevel.Debug, "DELETE request received (" + req.uri + ")");
						handleDelete(channel, req.uri, req.data);
					}
					else
					{
						throw new Exception("Invalid message format: " + request);
					}
		
		channel.close();
	}
	
	protected void handleGet(SocketChannel channel, String uri, String data) {
		
	}
	
	protected void handlePut(SocketChannel channel, String uri, String data) {
		
	}
	
	protected void handlePost(SocketChannel channel, String uri, String data) {
		
	}
	
	protected void handleDelete(SocketChannel channel, String uri, String data) {
		
	}
	
}

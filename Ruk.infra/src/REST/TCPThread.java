package REST;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import Logger.LogWriter;
import Logger.LogWriter.LogLevel;

public class TCPThread extends Thread {
	
	private SocketChannel _channel = null;
	IDataHandler _dataHandler;
	
	public TCPThread(SocketChannel channel, IDataHandler dataHandler) {
	    this._channel = channel;
	    _dataHandler = dataHandler;
	}
	
	@Override
	public void run() {
		
	    ByteBuffer buffer = ByteBuffer.allocate(2048);
	    
	    try {           
	        
	    	_channel.read(buffer);
	    	
	    	String data = new String(buffer.array());
	    	
	    	LogWriter.log(LogLevel.Debug, "Data: " + data);
	    	
	    	_dataHandler.handleData(data, _channel);
	    	
    	} catch (IOException e) {
    		e.printStackTrace();
    	} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    
	    try {
			_channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
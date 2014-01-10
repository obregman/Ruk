package REST;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

import Logger.LogWriter;
import Logger.LogWriter.LogLevel;

public class TCPServer {
	
	int _port = 4444;
	boolean _quit = false;
	IDataHandler _dataHandler;
	
	public TCPServer(int port, IDataHandler dataHandler) {
		_port = port;
		_dataHandler = dataHandler;
	}
	
	public void run() {
		
		_quit = false;
		
		try {
			
	        SocketAddress localport = new InetSocketAddress(_port);
	        ServerSocketChannel tcpserver = ServerSocketChannel.open();
	        tcpserver.socket().bind(localport);

	        tcpserver.configureBlocking(false);

	        Selector selector = Selector.open();

	        tcpserver.register(selector, SelectionKey.OP_ACCEPT);

	        LogWriter.log(LogLevel.Debug, "Server Sterted on port: " + _port + "!");

	        // Now loop forever, processing client connections
	        while( !_quit ) {
	            try { 
	                selector.select(100);
	                Set<SelectionKey> keys = selector.selectedKeys();

	                // Iterate through the Set of keys.
	                for (Iterator<SelectionKey> i = keys.iterator(); i.hasNext();) {
	                    SelectionKey key = i.next();
	                    i.remove();

	                    Channel c = key.channel();

	                    if (key.isAcceptable() && c == tcpserver) {
	                    	
	                    	LogWriter.log(LogLevel.Debug, "Connection accepted");
	                    	
	                        new TCPThread(tcpserver.accept(), _dataHandler).start();
	                    }
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.err.println(e);
	        System.exit(1);
	    }
	}
	
	public void quit() {
		_quit = true;
	}
}

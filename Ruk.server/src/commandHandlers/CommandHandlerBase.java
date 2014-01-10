package commandHandlers;

import java.nio.channels.SocketChannel;

import server.Server;

public class CommandHandlerBase {
	protected String _uri;
	protected Server _server;
	
	public CommandHandlerBase(Server server) {
		_server = server;
	}
	
	public String getURI() {
		return _uri;
	}
	
	public CHResult execute(SocketChannel channel, String uri, String data) {
		return new CHResult(CHResult.ResultStatus.NotImplemented);
	}
}

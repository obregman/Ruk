package commandHandlers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import server.Server;
import HTTP.HTTPResponse;

public class GetStatusHandler extends CommandHandlerBase {
	
	final String SERVICE_URI = "/ruk/ops/status";
	
	public GetStatusHandler(Server server) {
		super(server);
		_uri = SERVICE_URI;
	}
	
	public CHResult execute(SocketChannel channel, String uri, String data) {
		
		return new CHResult(CHResult.ResultStatus.Success, "Server is running");
	}

}

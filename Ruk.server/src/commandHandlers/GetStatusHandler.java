package commandHandlers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import srv.Server;
import HTTP.HTTPResponse;

public class GetStatusHandler extends CommandHandlerBase {
	
	public GetStatusHandler(Server server) {
		super(server);
		_uri = "/tysrv/ops/status";
	}
	
	public CHResult execute(SocketChannel channel, String uri, String data) {
		
		return new CHResult(CHResult.ResultStatus.Success, "Server is running");
	}

}

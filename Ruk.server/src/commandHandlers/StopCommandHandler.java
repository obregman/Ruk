package commandHandlers;

import java.nio.channels.SocketChannel;

import server.Server;

public class StopCommandHandler extends CommandHandlerBase {
	
	final String SERVICE_URI = "/ruk/ops/stop";
	
	public StopCommandHandler(Server server) {
		super(server);
		_uri = SERVICE_URI;
	}
	
	public CHResult execute(SocketChannel channel, String uri, String data) {
		_server.stop();
		return new CHResult(CHResult.ResultStatus.Success);
	}

}

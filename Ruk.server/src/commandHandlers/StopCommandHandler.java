package commandHandlers;

import java.nio.channels.SocketChannel;

import srv.Server;

public class StopCommandHandler extends CommandHandlerBase {
	
	public StopCommandHandler(Server server) {
		super(server);
		_uri = "/ruk/ops/stop";
	}
	
	public CHResult execute(SocketChannel channel, String uri, String data) {
		_server.stop();
		return new CHResult(CHResult.ResultStatus.Success);
	}

}

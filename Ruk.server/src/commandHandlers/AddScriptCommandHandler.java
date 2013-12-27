package commandHandlers;

import java.nio.channels.SocketChannel;

import srv.Server;

public class AddScriptCommandHandler extends CommandHandlerBase {
	
	public AddScriptCommandHandler(Server server) {
		super(server);
		_uri = "/ruk/ops/add-script";
	}
	
	public CHResult execute(SocketChannel channel, String uri, String data) {
		return new CHResult(CHResult.ResultStatus.Success, "Script was added [" + data + "]");
	}

}

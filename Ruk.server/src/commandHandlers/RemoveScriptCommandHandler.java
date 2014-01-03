package commandHandlers;

import java.nio.channels.SocketChannel;

import scriptParsers.ApiScript;
import srv.Server;

public class RemoveScriptCommandHandler extends CommandHandlerBase {
	
	ApiScript _apiScript = new ApiScript();
	
	public RemoveScriptCommandHandler(Server server) {
		super(server);
		_uri = "/ruk/ops/remove-script";
	}
	
	public CHResult execute(SocketChannel channel, String uri, String data) {
				
		
		return new CHResult(CHResult.ResultStatus.Success, "Failed to remove script [" + data + "]");
	}

}

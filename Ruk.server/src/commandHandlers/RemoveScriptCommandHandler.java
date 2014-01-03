package commandHandlers;

import java.nio.channels.SocketChannel;

import scripts.ApiScript;
import srv.Server;

public class RemoveScriptCommandHandler extends CommandHandlerBase {
	
	final String SERVICE_URI = "/ruk/ops/remove_script";
	
	ApiScript _apiScript = new ApiScript();
	
	public RemoveScriptCommandHandler(Server server) {
		super(server);
		_uri = SERVICE_URI;
	}
	
	public CHResult execute(SocketChannel channel, String uri, String data) {
				
		
		return new CHResult(CHResult.ResultStatus.Success, "Failed to remove script [" + data + "]");
	}

}

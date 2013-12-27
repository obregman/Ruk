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
		
		if( _apiScript.parse(data) ) {
			
			return new CHResult(CHResult.ResultStatus.Success, "Api script detected [" + _apiScript.dump() + "]");
			
		}
		
		return new CHResult(CHResult.ResultStatus.Success, "Failed to parse script [" + data + "]");
	}

}

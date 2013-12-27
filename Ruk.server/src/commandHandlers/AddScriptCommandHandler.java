package commandHandlers;

import java.nio.channels.SocketChannel;

import scriptParsers.ApiScript;
import scriptParsers.Script1;
import srv.Server;

public class AddScriptCommandHandler extends CommandHandlerBase {
	
	ApiScript _apiScript = new ApiScript();
	
	public AddScriptCommandHandler(Server server) {
		super(server);
		_uri = "/ruk/ops/add-script";
	}
	
	public CHResult execute(SocketChannel channel, String uri, String data) {
		
		if( _apiScript.parse(data) ) {
			_server.addApi(_apiScript);
			
			return new CHResult(CHResult.ResultStatus.Success, "Api script detected - " + _apiScript.getName() + " [" + _apiScript.dump() + "]");
		}
		
		return new CHResult(CHResult.ResultStatus.Success, "Failed to parse script [" + data + "]");
	}

}

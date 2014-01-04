package commandHandlers;

import java.nio.channels.SocketChannel;

import scripts.ApiScript;
import scripts.ScriptRunResult;
import server.Server;

public class DynamicApiCommandHandler extends CommandHandlerBase {
	
	ApiScript _apiScript;
	
	public DynamicApiCommandHandler(Server server) {
		super(server);
	}
	
	public void setName(String name) {
		_uri = "/ruk/ops/" + name;
	}
	
	public void setApiObject(ApiScript script) {
		_apiScript = script;
		setName(script.getName());
	}
	
	public CHResult execute(SocketChannel channel, String uri, String data) {
		
		ScriptRunResult result = _apiScript.run();
		
		if( result.success )
			return new CHResult(CHResult.ResultStatus.Success, result.response);
		else
			return new CHResult(CHResult.ResultStatus.Failed, result.errorMessage);
		
	}

}

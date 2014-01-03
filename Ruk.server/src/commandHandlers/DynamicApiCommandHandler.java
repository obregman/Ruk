package commandHandlers;

import java.nio.channels.SocketChannel;
import scripts.ApiScript;
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
				
		return new CHResult(CHResult.ResultStatus.Success, "DynamicApi CH executed");
	}

}

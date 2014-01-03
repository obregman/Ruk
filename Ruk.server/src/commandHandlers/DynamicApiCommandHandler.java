package commandHandlers;

import java.nio.channels.SocketChannel;

import server.Server;
import logic.ApiObject;

public class DynamicApiCommandHandler extends CommandHandlerBase {
	
	ApiObject _apiObj;
	
	public DynamicApiCommandHandler(Server server) {
		super(server);
	}
	
	public void setName(String name) {
		_uri = "/ruk/ops/" + name;
	}
	
	public void setApiObject(ApiObject apiObj) {
		_apiObj = apiObj;
	}
	
	public CHResult execute(SocketChannel channel, String uri, String data) {
				
		return new CHResult(CHResult.ResultStatus.Success, "DynamicApi CH executed");
	}

}

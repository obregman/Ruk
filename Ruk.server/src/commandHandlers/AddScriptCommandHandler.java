package commandHandlers;

import java.nio.channels.SocketChannel;

import scriptParsers.ApiScript;
import scriptParsers.ScriptBlock;
import scriptParsers.ScriptTree;
import scripts.Script;
import srv.Server;

public class AddScriptCommandHandler extends CommandHandlerBase {
	
	ApiScript _apiScript = new ApiScript();
	
	public AddScriptCommandHandler(Server server) {
		super(server);
		_uri = "/ruk/ops/add-script";
	}
	
	public CHResult execute(SocketChannel channel, String uri, String data) {
		
		ScriptBlock block = ScriptTree.buildTree(data);
		
		ScriptBlock block = findBlock("api");
		
		Script script = _apiScript.parse(data);
		if( script != null ) {
			_server.addScript(script);
			
			return new CHResult(CHResult.ResultStatus.Success, "Api script detected - " + _apiScript.getName() + " [" + _apiScript.dump() + "]");
		}
		
		return new CHResult(CHResult.ResultStatus.Success, "Failed to parse script [" + data + "]");
	}

}

package commandHandlers;

import java.nio.channels.SocketChannel;
import java.util.Dictionary;
import java.util.Hashtable;

import scriptParsers.ScriptBlock;
import scriptParsers.ScriptTree;
import scripts.ApiScript;
import scripts.Script;
import server.Server;

public class AddScriptCommandHandler extends CommandHandlerBase {
		
	final String SERVICE_URI = "/ruk/ops/add-script";
	
	Hashtable<String, Script> _scriptHandlers = new Hashtable<String, Script>(); 
	
	ApiScript _apiScript = new ApiScript();
	
	public AddScriptCommandHandler(Server server) {
		super(server);
		_uri = SERVICE_URI;
		
		ApiScript apiScript = new ApiScript();
		_scriptHandlers.put(apiScript.getType(), apiScript);
	}
	
	/**
	 * Executes the AddScript command handler
	 */
	public CHResult execute(SocketChannel channel, String uri, String data) {

		ScriptTree tree = ScriptTree.buildTree(data);
		
		String type = tree.getType();
		if ( _scriptHandlers.containsKey(type) ) {
			
			System.out.println(String.format("AddScriptCommand: Script of type '%s' detectrd", type));
			
			Script scriptObj = _scriptHandlers.get(type).getObject();
			
			if( scriptObj != null ) {
				boolean success = scriptObj.parse(tree);
				
				if( success ) {
					_server.addScript(scriptObj);
					return new CHResult(CHResult.ResultStatus.Success, "Api script detected - " + scriptObj.getName() + " [" + scriptObj.dump() + "]");
				}
			}
		}
			
		return new CHResult(CHResult.ResultStatus.Failed, "Failed to parse script [" + data + "]");
	}

}

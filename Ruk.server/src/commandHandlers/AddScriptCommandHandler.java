package commandHandlers;

import java.nio.channels.SocketChannel;
import java.util.Dictionary;
import java.util.Hashtable;

import scriptParsers.ScriptBlock;
import scriptParsers.ScriptTree;
import scripts.ApiScript;
import scripts.ImmediateScript;
import scripts.Script;
import scripts.ScriptRunResult;
import server.Server;

public class AddScriptCommandHandler extends CommandHandlerBase {
		
	final String SERVICE_URI = "add-script";
	
	Hashtable<String, Script> _scriptHandlers = new Hashtable<String, Script>(); 
	
	ApiScript _apiScript = new ApiScript();
	
	public AddScriptCommandHandler(Server server) {
		super(server);
		_uri = SERVICE_URI;
		
		registerScriptHandler(new ApiScript());
		registerScriptHandler(new ImmediateScript());
	}
	
	private void registerScriptHandler(Script scriptObj) {
		_scriptHandlers.put(scriptObj.getType(), scriptObj);
	}
	
	/**
	 * Executes the AddScript command handler
	 */
	@Override
	public CHResult execute(SocketChannel channel, String uri, String data) {

		ScriptTree tree = ScriptTree.buildTree(data);
		
		String type = tree.getType();
		if ( _scriptHandlers.containsKey(type) ) {
			
			System.out.println(String.format("AddScriptCommand: Script of type '%s' detectrd", type));
			
			Script scriptObj = _scriptHandlers.get(type).getObject();
			
			if( scriptObj != null ) {
				boolean success = scriptObj.parse(tree);
				
				if( success ) {
					if( scriptObj instanceof ApiScript ) {
						_server.addScript(scriptObj);
						return new CHResult(CHResult.ResultStatus.Success, String.format("%s script detected", type.toString()));						
					}
					else
						if( scriptObj instanceof ImmediateScript ) {
							ScriptRunResult result = scriptObj.run();
							if( result.success )
								return new CHResult(CHResult.ResultStatus.Success, result.response);
							else
								return new CHResult(CHResult.ResultStatus.Failed, result.errorMessage);
						}
					
				}
			}
		}
			
		return new CHResult(CHResult.ResultStatus.Failed, "Failed to process script");
	}

}

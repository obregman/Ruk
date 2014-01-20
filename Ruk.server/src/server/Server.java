package server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Dictionary;
import java.util.Hashtable;

import scripts.ApiScript;
import scripts.ImmediateScript;
import scripts.Script;
import server.RESTServer;
import commandHandlers.AddScriptCommandHandler;
import commandHandlers.CHResult;
import commandHandlers.CommandHandlerBase;
import commandHandlers.DynamicApiCommandHandler;
import commandHandlers.GetStatusHandler;
import commandHandlers.RemoveScriptCommandHandler;
import commandHandlers.StopCommandHandler;
import HTTP.HTTPResponse;
import processes.ProcessManager;
import REST.RESTService;

public class Server {
	
	final int PORT = 23111;
	final String BASE_URI = "/ruk/api/";
	
	Hashtable<String,CommandHandlerBase> _commandHandlers = new Hashtable<String, CommandHandlerBase>();	
	boolean _quit = false;
	boolean _running = false;
	ProcessManager _jobManager = new ProcessManager();
	RESTServer _restSrv;
	Workspace _workspace;
	
	public Server() {
		_restSrv = new RESTServer(this);
		_workspace = new Workspace(this);
		
		registerCommandHandlers();
	}
	
	private void registerCommandHandlers() {
		registerCommandHandler(new StopCommandHandler(this));
		registerCommandHandler(new GetStatusHandler(this));
		registerCommandHandler(new AddScriptCommandHandler(this));
		registerCommandHandler(new RemoveScriptCommandHandler(this));
		
	}
	
	private void registerCommandHandler(CommandHandlerBase handler) {
		_commandHandlers.put(BASE_URI + handler.getURI(), handler);
	}
	
	private void removeCommandHandler(String uri) {
		if( _commandHandlers.containsKey(uri) )
			_commandHandlers.remove(uri);
	}
	
	public void start() {
		
		if( _running )
			return;
		
		_quit = false;
		_restSrv.startListener(PORT);
		_running = true;
		
		while( !_quit ) {
			
			try {
				Thread.sleep(10);
			}
			catch(InterruptedException ex)
			{
			
			}
		}
		
		_jobManager.init();
		_restSrv.stopListener();
		_running = false;
	}
	
	public void stop() {
		_quit = true;
	}
	
	public void handleCommand(SocketChannel channel, String uri, String data) {

		CHResult result = CHResult.NotImplemented;
		if(_commandHandlers.containsKey(uri) )
			result = _commandHandlers.get(uri).execute(channel, uri, data);
		
		try {
			HTTPResponse response = new HTTPResponse();
			response.succeess = result.isSuccess();
			response.data = result.getJsonResponse();		
					
			channel.write(ByteBuffer.wrap(response.toString().getBytes()));
			channel.close();
		} catch(IOException ex) {}
	}
	
	public void addScript(Script script) {
		
		System.out.println(String.format("Server: Script object '%s' added", script.getType()));
		
		if( script instanceof ApiScript ) {
			
			if( _workspace.apiExists(script.getName())) {
				_workspace.removeApi(script.getName());
				removeCommandHandler("ruk/ops/" + script.getName());
			}
			
			DynamicApiCommandHandler dynamicCH = new DynamicApiCommandHandler(this);
			dynamicCH.setApiObject((ApiScript)script);
			registerCommandHandler(dynamicCH);
			_workspace.addApi((ApiScript)script);
		}		
	}

}

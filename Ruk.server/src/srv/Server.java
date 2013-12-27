package srv;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Dictionary;
import java.util.Hashtable;

import logic.Workspace;
import scriptParsers.ApiScript;
import srv.RESTServer;
import commandHandlers.AddScriptCommandHandler;
import commandHandlers.CHResult;
import commandHandlers.CommandHandlerBase;
import commandHandlers.GetStatusHandler;
import commandHandlers.RemoveScriptCommandHandler;
import commandHandlers.StopCommandHandler;
import HTTP.HTTPResponse;
import processes.ProcessManager;
import REST.RESTService;

public class Server {
	
	final int PORT = 23111;
	
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
		_commandHandlers.put(handler.getURI(), handler);
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
	
	public void addApi(ApiScript apiScript) {
		
	}

}

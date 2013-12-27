package HTTP;

public class HTTPRequest {
	
	public String operation;
	public String uri;
	public String data;
	
	public HTTPRequest() {
		
	}
	
	public HTTPRequest(String request) {
		parse(request);
	}
	
	private void parse(String request) {
		
		String[] tokens = request.split(" ");
		operation = tokens[0];
		if( tokens.length > 1 )
			uri = tokens[1];
				
		int newLinePos = request.indexOf("\r\n\r\n");
		if( newLinePos >= 0 ) {
			data = request.substring(newLinePos + 4, request.length() - 1);
		}		
	}

}

package HTTP;

public class HTTPResponse {
	
	final String HTTPVER = "HTTP/1.1";
	
	public boolean succeess;
	public int code;
	public String contentType = " text/xml; charset=utf-8";
	public String data;	
	
	public HTTPResponse() {
		
	}
	
	public HTTPResponse(String response) {
		parse(response);
	}
	
	private void parse(String response) {
		
		String tokens[] = response.split(" ");
		
		if( tokens.length < 2 )
			return;
		
		code = Integer.parseInt(tokens[1]);
		if( code == 200 )
			succeess = true;
		else
			succeess = false;
		
		int newLinePos = response.indexOf("\r\n\r\n");
		if( newLinePos >= 0 ) {
			data = response.substring(newLinePos + 4, response.length() - 1);
		}	
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(HTTPVER);
		
		if( succeess )
			sb.append(" OK 200");
		else
			sb.append(" Bad request 400");
			
		sb.append("\r\n");
		
		// Content-type
		sb.append("Content-Type: " + contentType + "\r\n");
		
		// Content-length
		if( data == null )
			sb.append("Content-Length: 0\r\n");
		else
			sb.append("Content-Length: " + data.length() + "\r\n");
		
		sb.append("\r\n" + data);
		
		return sb.toString();
	}
	
	public static HTTPResponse success() {
		HTTPResponse res = new HTTPResponse();
		res.code = 200;
		res.succeess = true;	
		return res;
	}

}

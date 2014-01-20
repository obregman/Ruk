package HTTP;

public class HTTPResponse {
	
	final String HTTPVER = "HTTP/1.1";
	
	public boolean succeess;
	public int code;
	public String contentType = "json";
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
			sb.append("HTTP/1.1 200 OK");
		else
			sb.append("400 Bad request");
			
		sb.append("\r\n");
		
		// Content-type
		sb.append("Content-Type: " + contentType + "\r\n");
		
		// Content-length
		if( data == null )
			sb.append("Content-Length: 0\r\n");
		else {
			int len = data.length();
			sb.append("Content-Length: " + len + "\r\n");
		}
		
		sb.append("Access-Control-Allow-Origin: *\r\n");
		sb.append("Cache-Control: no-cache\r\n");
		
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

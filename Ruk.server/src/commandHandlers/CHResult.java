package commandHandlers;

import javax.tools.StandardLocation;

public class CHResult {
	public enum ResultStatus {
		Success,
		NotImplemented,
		NotFound,
		Failed
	}
	
	private ResultStatus _status;
	private String _response;
	
	public static final CHResult NotImplemented = new CHResult(CHResult.ResultStatus.NotImplemented, "Not implemented");
	
	public CHResult(ResultStatus status) {
		_status = status;
	}
	
	public CHResult(ResultStatus status, String response) {
		_status = status;
		_response = response;
	}
	
	public ResultStatus getStatus() {
		return _status;
	}
	
	public String getJsonResponse() {
		return "{'success':'" + (_status == ResultStatus.Success) + "', 'response':'" + _response + "'}";
	}
	
	public void setRepsonse(String response) {
		_response = response;
	}
	
	public boolean isSuccess() {
		return _status == ResultStatus.Success;
	}
}

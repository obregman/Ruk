package scripts;

public class ScriptRunResult {
	
	public ScriptRunResult() {
		
	}
	
	public boolean success;
	public String response;
	public String errorMessage;

	public static ScriptRunResult failed(String errorMessage) {
		ScriptRunResult result = new ScriptRunResult();
		result.success = false;
		result.errorMessage = errorMessage;
		return result;
	}
	
	public static ScriptRunResult succeeded(String response) {
		ScriptRunResult result = new ScriptRunResult();
		result.success = true;
		result.response = response;
		return result;
	}
}

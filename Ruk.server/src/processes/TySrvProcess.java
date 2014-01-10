package processes;

import java.util.Date;

public class TySrvProcess {
	
	public enum ProcessStates {
		Disabled,
		Active,
		Running,
		Failed
	}
	
	protected String _id;
	protected String _name;
	protected ProcessStates _state;
	protected Date _created;
	protected Date _started;
	protected Date _finished;
	
	public TySrvProcess() {
		
	}
	
	public void run() {
		
	}
}

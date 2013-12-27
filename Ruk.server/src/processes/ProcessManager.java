package processes;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class ProcessManager {

	Hashtable<String,TySrvProcess> _processes = new Hashtable<String, TySrvProcess>();
	
	public ProcessManager() {
		
	}
	
	public void init() {
		_processes.clear();
	}
	
	public void addProcess(TySrvProcess process) {
		_processes.put(process._id, process);
	}
	
	public TySrvProcess getProcessByName(String name) {
		return null;
	}
	
	public TySrvProcess getProcess(String id) {
		if(_processes.containsKey(id) )
			return _processes.get(id);
		else
			return null;
	}
	
	public void removeProcess(String id) {
		_processes.remove(id);
	}
	
	public void runProcess(String id) {
		TySrvProcess process = getProcess(id);
		process.run();
	}
	
	public void disableProcess(String id) {
		
	}
	
	public void ActivateProcess(String id) {
		
	}
}

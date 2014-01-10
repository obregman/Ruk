package Logger;

public class LogWriter {
	
	public enum LogLevel {
		Verbose,
		Debug,
		Warning,
		Error
	}
		
	static LogLevel _maxLogLevel = LogLevel.Debug;
	static boolean _writeToConsole = true;	

	public static void setLogLevel(LogLevel maxLogLevel) {
		_maxLogLevel = maxLogLevel;
	}
	
	public static void writeToConsole(boolean value) {
		_writeToConsole = value;
	}
	
	public static void log(LogLevel logLevel, String text) {
		
		if( logLevel.ordinal() >= _maxLogLevel.ordinal() ) {
			
			writeLog(logLevel, text);
		}
	}
	
	private static void writeLog(LogLevel logLevel, String text) {
		if ( _writeToConsole ) {
			
			System.out.println(logLevel.toString() + " | " + text);
		}
	}
}

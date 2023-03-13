package telran.io.util;

import java.time.Instant;
import java.time.ZoneId;

public class Logger {
	private Level level;
	private Handler handler;
	private String name;

	public Logger(Handler handler, String name) {
		this.handler = handler;
		this.name = name;
		this.level = Level.INFO;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void error(String message) {
		LoggerRecord logRec = createLogRec(message, Level.ERROR);
		handler.publish(logRec);
	}

	public void warn(String message) {
		LoggerRecord logRec = createLogRec(message, Level.WARN);
		handler.publish(logRec);
	}

	public void info(String message) {
		LoggerRecord logRec = createLogRec(message, Level.INFO);
		handler.publish(logRec);
	}

	public void debug(String message) {
		LoggerRecord logRec = createLogRec(message, Level.DEBUG);
		handler.publish(logRec);
	}

	public void trace(String message) {
		LoggerRecord logRec = createLogRec(message, Level.TRACE);
		handler.publish(logRec);
	}

	private LoggerRecord createLogRec(String message, Level level) {
		return new LoggerRecord(Instant.now(), ZoneId.systemDefault().toString(), level, name, message);
	}
}
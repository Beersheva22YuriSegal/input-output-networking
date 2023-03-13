package telran.io.util.test;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.io.util.Level;
import telran.io.util.Logger;
import telran.io.util.SimpleStreamHandler;

class LoggerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void consoleTest() {
		Logger log = new Logger(new SimpleStreamHandler(System.out), "Logger");
		log.setLevel(Level.ERROR);
		log.error("Error message");
		log.warn("Warning message");
		log.info("Info message");
		log.debug("Debug message");
		log.trace("Trace message");
	}
	
	@Test
	void fileTest() throws FileNotFoundException {
		String logFile = "logger.txt";
		SimpleStreamHandler handler = new SimpleStreamHandler(new PrintStream(logFile));
		Logger log = new Logger(handler, "Logger");
		log.setLevel(Level.ERROR);
		log.error("Error message");
		log.warn("Warning message");
		log.info("Info message");
		log.debug("Debug message");
		log.trace("Trace message");	
	}
}

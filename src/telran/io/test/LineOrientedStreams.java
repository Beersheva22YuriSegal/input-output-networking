package telran.io.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.util.stream.IntStream;

public class LineOrientedStreams {
	static final String fileNamePrintSream = "lines-stream.txt";
	static final String filenamePrintWriter = "lines-writer.txt";
	static final String line = "Hello World";
	static final String helloFileName = "test.txt";
	private static final int N_RECORDS = 100_000_000;

	@Test
	@Disabled
	void printStreamTest() throws Exception {
		PrintStream printStream = new PrintStream(fileNamePrintSream);
		IntStream.range(0, N_RECORDS).forEach(printStream::println);
	}

	@Test
	@Disabled
	void printWriterTest() throws Exception {
		try (PrintWriter printWriter = new PrintWriter(filenamePrintWriter)) {
			IntStream.range(0, N_RECORDS).forEach(printWriter::println);
		}
	}
	
	@Test
	void bufferedReaderTest() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(helloFileName));
//		while (true) {
//			String nextLine = reader.readLine();
//			if (nextLine == null) {
//				break;
//			}
//			assertEquals(line, nextLine);
//		}
		reader.lines().parallel().forEach(l -> assertEquals(line, l));
	}
}
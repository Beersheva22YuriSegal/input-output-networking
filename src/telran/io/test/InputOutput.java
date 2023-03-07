package telran.io.test;

import java.io.*;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class InputOutputTest {

	private final static int OFFSET = 4;
	String fileName = "myFile";
	String directoryName = "myDirectory1/myDirectory2";

	@BeforeEach
	void setUp() throws Exception {
		new File(fileName).delete();
		new File(directoryName).delete();
	}

	@Test
	@Disabled
	void testFile() throws IOException {
		File f1 = new File(fileName);
		assertTrue(f1.createNewFile());
		File dir1 = new File(directoryName);
		assertTrue(dir1.mkdirs());
		System.out.println(dir1.getAbsolutePath());
	}

	@Test
	@Disabled
	void printDirectoryFileTest() {
		printDirectoryFile(".", 0);
	}

	void printDirectoryFile(String path, int maxLevel) {
		if (maxLevel < 1) {
			maxLevel = Integer.MAX_VALUE;
		}
		File dir = new File(path).getAbsoluteFile();
		printDirectoryFile(dir, maxLevel, 0);
	}

	private void printDirectoryFile(File dir, int maxLevel, int currentLevel) {
		File[] files = dir.listFiles();
		for (File f : files) {
			if (f.isDirectory() && maxLevel != 1) {
				printing(currentLevel, f);
				printDirectoryFile(f, maxLevel, currentLevel + OFFSET);
			} else {
				printing(currentLevel, f);
			}
		}
	}

	private void printing(int offset, File f) {
		String fileOrDir = f.isDirectory() ? " - Directory" : " - File";
		System.out.printf("%s%s%s\n", " ".repeat(offset), "<" + f.getName() + ">", fileOrDir);

	}

	@Test
	@Disabled
	void testFiles() {
		Path path = Path.of(".");
		System.out.println(path.toAbsolutePath().getNameCount());
	}

	@Test
	// @Disabled
	void printDirectoryFilesTest() throws IOException {
		printDirectoryFiles(".", 0);
	}

	void printDirectoryFiles(String path, int maxLevel) throws IOException {
		if (maxLevel < 1) {
			maxLevel = Integer.MAX_VALUE;
		}
		Path dir = Path.of(path);
		Files.walk(dir, maxLevel).filter(p -> p != dir).forEach(
				p -> System.out.printf("%s%s%s\n", " ".repeat(p.getNameCount() * OFFSET), p.getFileName(), getType(p)));
	}

	private String getType(Path file) {
		return Files.isDirectory(file) ? " - Directory" : " - file";
	}
}
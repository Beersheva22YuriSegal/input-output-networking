package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class TransferCopy extends Copy {
	
	public TransferCopy(String srcFilePath, String destFilePath, boolean overwrite) {
	super(srcFilePath, destFilePath, overwrite);
	}

	@Override
	public long copy() {
		long res;
		try (InputStream input = new FileInputStream(srcFilePath);
				OutputStream output = new FileOutputStream(destFilePath)) {
			res = input.transferTo(output);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return res;
	}

	@Override
	DisplayResult getDisplayResult(long copyTime, long fileSize) {
		return new DisplayResult(fileSize, copyTime);
	}
}

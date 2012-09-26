package Verify;

import java.io.File;

public interface VerifyImage {
	String exec(String path);

	String exec(File file);
}

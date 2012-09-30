package Module.Data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author YuFeng
 * @version 1.0
 * @created 13-四月-2011 8:31:57
 */
public class DataWriter {
	BufferedWriter w;

	public DataWriter(String filename) throws IOException {
		this(new FileWriter(filename));
	}
                public DataWriter(String filename,boolean b) throws IOException {
		this(new FileWriter(filename, b));
	}

	public DataWriter(FileWriter wr) {
		w = new BufferedWriter(wr);
	}

	public void close() {
		try {
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param s
	 */
	public void write(String s) {
		try {
			w.write(s);
			w.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeLine(String s) {
		try {
			w.write(s + "\r\n");
			w.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

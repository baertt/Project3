package Controllers;

import java.io.File;

public class FileChecker {
	public Boolean fileChecker(String filename) {
		File f = new File(filename + ".db");
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;
	}
}

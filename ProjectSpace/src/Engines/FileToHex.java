package Engines;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileToHex {
	public static String convertToHex(File file) {
		StringBuilder sbResult = null;
		try {
			InputStream is = new FileInputStream(file);

			int bytesCounter = 0;
			int value = 0;
			StringBuilder sbHex = new StringBuilder();
			StringBuilder sbText = new StringBuilder();
			sbResult = new StringBuilder();

			while ((value = is.read()) != -1) {
				// convert to hex value with "X" formatter
				sbHex.append(String.format("%02X ", value));

				// If the chracater is not convertable, just print a dot symbol "."
//				if (!Character.isISOControl(value)) {
//					sbText.append((char) value);
//				} else {
//					sbText.append(".");
//				}

				// if 16 bytes are read, reset the counter,
				// clear the StringBuilder for formatting purpose only.
				if (bytesCounter == 15) {
					sbResult.append(sbHex);
					sbHex.setLength(0);
					sbText.setLength(0);
					bytesCounter = 0;
				} else {
					bytesCounter++;
				}
			}

			// if still got content
			if (bytesCounter != 0) {
				// add spaces more formatting purpose only
				for (; bytesCounter < 16; bytesCounter++) {
					// 1 character 3 spaces
					sbHex.append(" ");
				}
				sbResult.append(sbHex).append(" ").append(sbText).append("\n");
			}

			is.close();
		} catch (Exception e) {
		}
		return sbResult.toString();
	}

	public static String init(File target) {
		String hex = null;
		try {
			hex = convertToHex(new File(target.toString()));
		} catch (Exception e) {
			hex = "==FAIL==";
			e.printStackTrace();
		}
		return hex;
	}

}

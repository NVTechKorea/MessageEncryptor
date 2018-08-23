package Engines;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DreamOS.DeleteFile;

public class DownloadHelper {
	private String var = null;
	public void copyURLToFile(URL url, File file, String process) {
		try {
			InputStream input = url.openStream();
			if (file.exists()) {
				if (file.isDirectory())
					throw new IOException("File '" + file + "' is a directory");
				if (!file.canWrite())
					throw new IOException("File '" + file + "' cannot be written");
			} else {
				File parent = file.getParentFile();
				if ((parent != null) && (!parent.exists()) && (!parent.mkdirs())) {
					throw new IOException("File '" + file + "' could not be created");
				}
			}
			FileOutputStream output = new FileOutputStream(file);
			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}
			input.close();
			output.close();
			System.out.println("INFO [DL]: Complete.");
			if(process.equals("updateServer")) {
				dialogBox("Download complete.", "Update Helper");
			}
		} catch (IOException ioEx) {
			if(process.equals("verificationServer")) {
				JFrame warningFrame = new JFrame("Error");
				JOptionPane.showMessageDialog(warningFrame, "Unable to establish connection between verification server.");
				System.out.println("ERROR [DL]: Unable to establish connection between verification server.");
				DeleteFile df = new DeleteFile();
				df.initiate(var, true);
				System.exit(0);
			}else if (process.equals("getUpdateInfo")){
				JFrame warningFrame = new JFrame("Error");
				JOptionPane.showMessageDialog(warningFrame, "Unable to establish connection between update server.");
				System.out.println("ERROR [DL]: Unable to establish connection between update server.");
			}else if (process.equals("updateServer")){
				JFrame warningFrame = new JFrame("Error");
				JOptionPane.showMessageDialog(warningFrame, "Unable to establish connection between update server.");
				System.out.println("ERROR [DL]: Unable to establish connection between update server.");
			}else {
				ioEx.printStackTrace();
				System.exit(0);
			}
		}
	}
	public void dialogBox(String Msg, String title) {
		String informationString = Msg;
		JOptionPane.showMessageDialog(null, informationString, title, JOptionPane.PLAIN_MESSAGE);
	}
	public void regvar(String vvar) {
		var = vvar;
	}
	public void initiate(String sUrl, String path, String process) {
		try {
			URL url = new URL(sUrl);
			File file = new File(path);
			copyURLToFile(url, file, process);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}

package Engines;

import DreamOS.ReadFile;
import DreamOS.DeleteFile;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.File;

public class SignatureCheck_New {
	private String signatureFileLoc = null;
	private String signatureFileLink = null;
	private String ver = "1.7";
	private String var = null;

	public SignatureCheck_New() {
	}

	public void regvar(String path, String vvar) {
		signatureFileLoc = path + "SignatureFile.signdoc";
		var = vvar;
		signatureFileLink = "https://raw.githubusercontent.com/NVTechKorea/MessageEncryptor/master/VerificationData/SignatureFile_"
				+ ver + ".signdoc";
	}

	public void initiate() {
		boolean pass = false;
		pass = check();
		if (!pass) {
			showWarning("Unable to execute program.\nFailed to check integrity.");
		} else {
			System.out.println("INFO [SIG]: Integrity check was successful.");
		}
	}

	public boolean check() {
		File signatureFile = new File(signatureFileLoc);
		boolean pass = false;
		ReadFile rf = new ReadFile();
		for (;;) {
			if (signatureFile.exists()) {
				String data = rf.initiate(signatureFileLoc);
				if (data.startsWith("GITVERFSTART")) {
					String parsable[] = data.split("<.>");
					int parsableLength = parsable.length;
					if (parsableLength == 3) {
						String[] signverifier = parsable[1].split("-");
						if (signverifier[0].equals(ver)) {
							if (signverifier[1].equals("signed")) {
								pass = true;
								break;
							} else {
								showWarning("This version is no longer being signed.");
							}
						} else {
							showWarning("Signature version does not match.");
						}
					} else {
						showWarning("Failed parsing signature file.");
					}
				} else {
					showWarning("Broken signature file.");
				}
			} else {
				DownloadHelper dl = new DownloadHelper();
				dl.regvar(var);
				dl.initiate(signatureFileLink, signatureFileLoc, "verificationServer");
				pass = false;
			}
		}
		return pass;
	}

	public void showWarning(String warningMsg) {
		JFrame warningFrame = new JFrame("Error");
		JOptionPane.showMessageDialog(warningFrame, warningMsg);
		System.out.println("ERROR [SIG]: " + warningMsg);
		DeleteFile df = new DeleteFile();
		df.initiate(signatureFileLoc, true);
		df.initiate(var, true);
		System.exit(0);
	}
}

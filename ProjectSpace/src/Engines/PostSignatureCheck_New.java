package Engines;

import DreamOS.ReadFile;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.File;

public class PostSignatureCheck_New {
	private String signatureFileLoc = null;
	private String signatureFileLink = null;
	private String ver = "1.7";
	public PostSignatureCheck_New() {}
	public void regvar(String path) {
		signatureFileLoc = path + "SignatureFile.signdoc";
		signatureFileLink = "https://raw.githubusercontent.com/NVTechKorea/MessageEncryptor/master/VerificationData/SignatureFile_" + ver + ".signdoc";
	}

	public void initiate() {
		File signatureFile = new File(signatureFileLoc);
		ReadFile rf = new ReadFile();
		DownloadHelper dl = new DownloadHelper();
		dl.initiate(signatureFileLink, signatureFileLoc, "verificationServer-post");
		if (signatureFile.exists()){
			String data = rf.initiate(signatureFileLoc);
			if (data.startsWith("GITVERFSTART")) {
				String parsable[] = data.split("<.>");
				int parsableLength = parsable.length;
				if (parsableLength == 3) {
					String[] signverifier = parsable[1].split("-");
					if (signverifier[0].equals(ver)) {
						if (signverifier[1].equals("signed")) {
						} else {
							showWarning("This version is no longer being signed.\nWe recommend you to update.");
						}
					}
				}
			}
		}
	}

	public void showWarning(String warningMsg) {
		JFrame warningFrame = new JFrame("Warning");
		JOptionPane.showMessageDialog(warningFrame, warningMsg);
		System.out.println("WARN [SIG]: " + warningMsg);
	}
}

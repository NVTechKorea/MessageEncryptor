package Engines;

import DreamOS.ReadFile;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.File;

////////////////WARNING///////////////////
//This class is no longer used
//since the signing format of 1.7 has
//changed.
//This class is only for 1.6 or lower.
//////////////////////////////////////////

public class PostSignatureCheck {
	private String signatureFileLoc = null;
	private String signatureFileLink = null;
	private String ver = "1.6";
	public PostSignatureCheck() {}
	public void regvar(String path) {
		signatureFileLoc = path + "SignatureFile.signdoc";
		signatureFileLink = "https://raw.githubusercontent.com/NVTechKorea/MessageEncryptor/master/VerificationData/SignatureFile_" + ver + ".signdoc";
	}

	public void initiate() {
		File signatureFile = new File(signatureFileLoc);
		ReadFile rf = new ReadFile();
		DownloadHelper dl = new DownloadHelper();
		dl.initiate(signatureFileLink, signatureFileLoc, "verificationServer");
		if (signatureFile.exists()){
			String data = rf.initiate(signatureFileLoc);
			if (data == null) {
				showWarning("Warning: Signature file is empty.");
			} else if (data.contains("UNSIGNED")) {
				showWarning("Warning: This version is no longer supported. We recommend you to update.");
			}
		}
	}

	public void showWarning(String warningMsg) {
		JFrame warningFrame = new JFrame("Warning");
		JOptionPane.showMessageDialog(warningFrame, warningMsg);
		System.out.println("WARN [SIG]: " + warningMsg);
	}
}

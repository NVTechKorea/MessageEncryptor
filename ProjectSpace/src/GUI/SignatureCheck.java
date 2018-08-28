package GUI;

import DreamOS.ReadFile;
import Engines.DownloadHelper;
import DreamOS.EncryptTool;
import DreamOS.DeleteFile;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.File;

public class SignatureCheck {
	private String signatureFileLoc = null;
	private String splitter = "wiSiAlNOwEh8UoBQhLVkuA==";
	private String signatureFileLink = null;
	private String ver = "1.6";
	private String var = null;
	public SignatureCheck() {}
	public void regvar(String path, String vvar) {
		signatureFileLoc = path + "SignatureFile.signdoc";
		var = vvar;
		signatureFileLink = "https://raw.githubusercontent.com/NVTechKorea/MessageEncryptor/master/VerificationData/SignatureFile_" + ver + ".signdoc";
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
		for(;;) {
			if (signatureFile.exists()) {
				String data = rf.initiate(signatureFileLoc);
				if (data == null) {
					showWarning("Unable to execute program.\nVERF_FAIL_SIGFILE_EMPTY");
				} else if (data.contains("SIGNATURE:" + ver)) {
					String[] parse = data.split(":");
					try {
						if (!parse[0].equals("SIGNATURE")) {
							showWarning("Unable to execute program.\nVERF_FAIL_SIGFILE_NOSIGFOUND");
							break;
						}else {
							if (!parse[1].equals(ver)) {
								showWarning("Unable to execute program.\nVERF_FAIL_SIGFILE_VERNOTMATCH");
								break;
							}else {
								if (!parse[3].equals("SIGNED")) {
									showWarning("Unable to execute program.\nVERF_FAIL_SIGFILE_UNSIG");
									break;
								}else {
									String encryptSignOrig = "firm.signed.ver=" + ver;
									String encryptSign = EncryptTool.aesEncrypt(encryptSignOrig, "alpine");
									encryptSignOrig = "oFuxbpSV1gga5pMhOJ9P7w==" + splitter + encryptSign;
									if (!parse[2].equals(encryptSignOrig)) {
										showWarning("Unable to execute program.\nVERF_FAIL_SIGFILE_UNVERF");
										break;
									} else {
										String[] parsable = parse[2].split(splitter);
										String verification = EncryptTool.aesDecrypt(parsable[1], "alpine");
										if (!verification.equals("firm.signed.ver=" + ver)) {
											showWarning("Unable to execute program.\nVERF_FAIL_SIGFILE_UNVERF");
											break;
										} else {
											pass = true;
											break;
										}
									}
								}
							}
						}
	
					} catch (Exception e) {
						String error = e.toString();
						if (error.contains("ArrayIndexOutOfBoundsException")) {
							showWarning("Unable to execute program.\nParse failed.");
						} else {
							showWarning("There was an error during verification.\n Error: " + error);
						}
					}
				} else {
					showWarning("Unable to execute program.\nVERF_FAIL_SIGFILE_VERNOTMATCH");
					break;
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

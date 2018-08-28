package GUI;

//import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import DreamOS.*;
import Engines.DownloadHelper;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class DreamOS_Core extends JFrame {
	private static final long serialVersionUID = 1L;
	private final String ver = "1.6";
	private final String programManu = "DreamProjectGroup";
	private final String programName = "DRMessageEncryptor";
	private JPanel contentPane;
	private JTextArea MsgToConvert;
	private JTextArea MsgResult;
	private JTextField PassKey;
	private JScrollPane scroll1;
	private JScrollPane scroll2;

	private JLabel lblWrongPasscode;
	private JLabel lblDecryptSuccess;
	private JLabel lblEncryptSuccess;
	private JLabel lblDownloading;
	private JLabel lblNoHeader;

	private String msg2convert = null;
	private String key = null;
	private String path;

	private boolean encrypt = true;

	private JButton btnEncrypt;
	private JButton btnDecrypt;
	private JButton btnAbout;
	private JButton btnUpdate;
	private JButton btnDecryptlegacy;
	private JButton btnEncryptlegacy;

	private String headerMain = "oFuxbpSV1gga5pMhOJ9P7w==";
	private String splitter = "wiSiAlNOwEh8UoBQhLVkuA==";
	private String header = headerMain + splitter;

	private boolean endecryptLegacy = false;

	public void initiate() {
		initNotifier();
		update(true);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DreamOS_Core frame = new DreamOS_Core();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DreamOS_Core() {
		this.setResizable(false);

		setTitle("DRMessageEncryptor " + ver);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		MsgToConvert = new JTextArea();
		MsgToConvert.setWrapStyleWord(true);
		MsgToConvert.setLineWrap(true);
		MsgToConvert.setBounds(6, 24, 438, 151);
		MsgToConvert.setColumns(10);

		MsgResult = new JTextArea();
		MsgResult.setLineWrap(true);
		MsgResult.setWrapStyleWord(true);
		MsgResult.setBounds(6, 204, 438, 87);
		MsgResult.setColumns(10);

		scroll1 = new JScrollPane(MsgToConvert);
		scroll1.setBounds(6, 24, 438, 151);
		scroll2 = new JScrollPane(MsgResult);
		scroll2.setBounds(6, 204, 438, 87);
		this.getContentPane().add(scroll1);
		this.getContentPane().add(scroll2);
		MsgToConvert.setCaretPosition(MsgToConvert.getDocument().getLength());
		MsgResult.setCaretPosition(MsgResult.getDocument().getLength());
		contentPane.add(scroll1);
		contentPane.add(scroll2);

		PassKey = new JTextField();
		PassKey.setBounds(6, 315, 438, 26);
		contentPane.add(PassKey);
		PassKey.setColumns(10);

		JLabel lblMessageToConvert = new JLabel("Message to convert");
		lblMessageToConvert.setBounds(6, 6, 183, 16);
		contentPane.add(lblMessageToConvert);

		JLabel lblConvertedResult = new JLabel("Converted result");
		lblConvertedResult.setBounds(6, 187, 183, 16);
		contentPane.add(lblConvertedResult);

		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setBounds(98, 341, 91, 29);
		contentPane.add(btnEncrypt);

		btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setBounds(6, 341, 91, 29);
		contentPane.add(btnDecrypt);

		JLabel lblPasscode = new JLabel("Passcode");
		lblPasscode.setBounds(6, 300, 91, 16);
		contentPane.add(lblPasscode);

		btnAbout = new JButton("About");
		btnAbout.setBounds(362, 341, 82, 29);
		contentPane.add(btnAbout);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(284, 341, 82, 29);
		contentPane.add(btnUpdate);

		lblWrongPasscode = new JLabel("Wrong passcode");
		lblWrongPasscode.setHorizontalAlignment(SwingConstants.CENTER);
		lblWrongPasscode.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblWrongPasscode.setForeground(Color.RED);
		lblWrongPasscode.setBounds(185, 346, 103, 16);
		lblWrongPasscode.setVisible(false);
		contentPane.add(lblWrongPasscode);

		lblDecryptSuccess = new JLabel("Decryption Successful");
		lblDecryptSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblDecryptSuccess.setForeground(Color.GREEN);
		lblDecryptSuccess.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblDecryptSuccess.setBounds(185, 346, 103, 16);
		lblDecryptSuccess.setVisible(false);
		contentPane.add(lblDecryptSuccess);

		lblEncryptSuccess = new JLabel("Encryption Successful");
		lblEncryptSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblEncryptSuccess.setForeground(Color.GREEN);
		lblEncryptSuccess.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblEncryptSuccess.setBounds(185, 346, 103, 16);
		lblEncryptSuccess.setVisible(false);
		contentPane.add(lblEncryptSuccess);

		lblDownloading = new JLabel("Downloading...");
		lblDownloading.setHorizontalAlignment(SwingConstants.CENTER);
		lblDownloading.setForeground(Color.GREEN);
		lblDownloading.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblDownloading.setBounds(185, 346, 103, 16);
		lblDownloading.setVisible(false);
		contentPane.add(lblDownloading);

		lblNoHeader = new JLabel("No header found.");
		lblNoHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoHeader.setForeground(Color.RED);
		lblNoHeader.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		lblNoHeader.setBounds(185, 346, 103, 16);
		lblNoHeader.setVisible(false);
		contentPane.add(lblNoHeader);

		btnDecryptlegacy = new JButton("Decrypt (Legacy)");
		btnDecryptlegacy.setBounds(6, 372, 148, 29);
		contentPane.add(btnDecryptlegacy);

		btnEncryptlegacy = new JButton("Encrypt (Legacy)");
		btnEncryptlegacy.setBounds(156, 372, 148, 29);
		contentPane.add(btnEncryptlegacy);

		print("Object creation process finished.");
		buttonListener();
	}

	public void initNotifier() {
		print("Core initiated.");
	}

	public void readInput() {
		msg2convert = MsgToConvert.getText();
		key = PassKey.getText();
	}

	public String calculate() {
		String result;
		if (encrypt) {
			result = EncryptTool.aesEncrypt(msg2convert, key);
			result = header + result;
			print("Encryption complete.");
			lblEncryptSuccess.setVisible(true);
		} else {
			if (!msg2convert.startsWith(header)) {
				lblNoHeader.setVisible(true);
				result = " ";
			} else {
				String[] parse = msg2convert.split(splitter);
				msg2convert = parse[1];
				result = EncryptTool.aesDecrypt(msg2convert, key);
				print("Decryption complete.");
				lblDecryptSuccess.setVisible(true);
			}
		}
		return result;
	}

	public void showOutput(String result) {
		MsgResult.setText(result);
	}

	public boolean checkRequiredFields() {
		boolean allFilled = false;
		String field1 = MsgToConvert.getText();
		String field3 = PassKey.getText();
		if (endecryptLegacy) {
			field1 = "LEG";
			field3 = "LEG";
			endecryptLegacy = false;
		}
		if (field1.equals("") || field3.equals("")) {
			showWarning("Require field is not completed or result box is filled.");
			allFilled = false;
		} else {
			allFilled = true;
		}
		return allFilled;
	}

	public void encryptButtonAction() {
		encrypt = true;
		boolean checked = checkRequiredFields();
		if (checked) {
			readInput();
			String result = calculate();
			showOutput(result);

		}
	}

	public void decryptButtonAction() {
		encrypt = false;
		boolean checked = checkRequiredFields();
		if (checked) {
			readInput();
			String result = calculate();
			if (result.equals(":ERR::WRONG::PW")) {
				disableAllMsgCode();
				lblWrongPasscode.setVisible(true);
			} else {
				showOutput(result);
			}
		}
	}

	public void showWarning(String warningMsg) {
		JFrame warningFrame = new JFrame("Warning");
		JOptionPane.showMessageDialog(warningFrame, warningMsg);
		error(warningMsg);
	}

	public void disableAllMsgCode() {
		lblWrongPasscode.setVisible(false);
		lblDownloading.setVisible(false);
		lblDecryptSuccess.setVisible(false);
		lblEncryptSuccess.setVisible(false);
		lblNoHeader.setVisible(false);
	}

	public void buttonListener() {
		print("Waiting for an action...");
		btnEncryptlegacy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableAllMsgCode();
				showWarning("You are trying to use the old encryption engine (1.5 or below).");
				endecryptLegacy = true;
				encryptButtonAction();
			}
		});
		btnDecryptlegacy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableAllMsgCode();
				endecryptLegacy = true;
				decryptButtonAction();
			}
		});
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableAllMsgCode();
				update(false);
			}
		});
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableAllMsgCode();
				calculationEngine(false);
			}
		});
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableAllMsgCode();
				calculationEngine(true);
			}
		});
	}

	public void calculationEngine(boolean encrypt) {
		boolean pass = checkRequiredFields();
		String finalMessage = null;
		if (pass) {
			if (encrypt) {
				String passcode = PassKey.getText();
				String contents = MsgToConvert.getText();
				int protectionalPasscode = passcode.length();
				String protectionalPasscodeInString = protectionalPasscode + "StringConverted";
				String newHeader = EncryptTool.aesEncrypt(protectionalPasscodeInString, "length");
				String firstEncryption = EncryptTool.aesEncrypt(contents, passcode);
				String secondEncryption = EncryptTool.aesEncrypt(firstEncryption, protectionalPasscodeInString);
				finalMessage = "[START_OF_DATA]<splitTag>" + newHeader + secondEncryption
						+ "<splitTag>[END_OF_CONTENT]<splitTag>WRITTEN_VER: " + ver + "<splitTag>[END_OF_DATA]";
				lblEncryptSuccess.setVisible(true);
			} else {
				String passcode = PassKey.getText();
				String contents = MsgToConvert.getText();
				if (contents.contains("[START_OF_DATA]<splitTag>") && contents.contains("[END_OF_DATA]")) {
					try {
						int protectionalPasscode = passcode.length();
						String protectionalPasscodeInString = protectionalPasscode + "StringConverted";
						String newHeader = EncryptTool.aesEncrypt(protectionalPasscodeInString, "length");
						String firstParse[] = contents.split(newHeader);
						int getFirstParseLeng = firstParse.length;
						if(getFirstParseLeng==1) {
							disableAllMsgCode();
							lblWrongPasscode.setVisible(true);
							finalMessage = "";
						}else {
							print(firstParse[1]);
							String removedTail[] = firstParse[1].split("<splitTag>");
							print(removedTail.length + "");
							print(removedTail[0]);
							String firstDecryption = EncryptTool.aesDecrypt(removedTail[0], protectionalPasscodeInString);
							if (firstDecryption.equals(":ERR::WRONG::PW")) {
								disableAllMsgCode();
								lblWrongPasscode.setVisible(true);
								finalMessage = "";
							} else {
								print(firstDecryption);
								finalMessage = EncryptTool.aesDecrypt(firstDecryption, passcode);
								print(finalMessage);
								lblDecryptSuccess.setVisible(true);
							}
						}
					} catch (Exception e) {
						String er = e.toString();
						showWarning("Unable to continue.\nThe program ran into panic: \n" + er);
						e.printStackTrace();
					}
				} else {
					disableAllMsgCode();
					lblNoHeader.setVisible(true);
				}
			}
			MsgResult.setText(finalMessage);
		}
	}

	public void update(boolean atLaunch) {
		lblDownloading.setVisible(true);
		print("Running update tool...");
		print("Getting information...");
		OSReader os = new OSReader();
		DownloadHelper dlhelper = new DownloadHelper();
		String[] osdata = os.initiate();
		String OSKind = osdata[1];
		OSKind = OSKind.toLowerCase();
		String sUrl = "https://raw.githubusercontent.com/NVTechKorea/MessageEncryptor/master/VerificationData/Latest.signdoc";
		path = osdata[2] + osdata[0] + programManu + osdata[0] + programName + osdata[0] + ver + osdata[0] + "storage"
				+ osdata[0] + "Latest.signdoc";
		dlhelper.initiate(sUrl, path, "getUpdateInfo");
		ReadFile rf = new ReadFile();
		String LatestVer = rf.initiate(path);
		if (LatestVer != null) {
			if (!LatestVer.equals(ver)) {
				if (atLaunch) {
					dialogBox("Update found: " + LatestVer + "\nCurrent version: " + ver
							+ "\nClose this window to download latest version.", "Update found");
				}
				if (OSKind.contains("windows")) {
					print("Downloading for Windows...");
					sUrl = "https://github.com/NVTechKorea/MessageEncryptor/raw/master/Windows/" + LatestVer
							+ "/MessageEncryptor.zip";
				} else if (OSKind.contains("mac")) {
					print("Downloading for macOS...");
					sUrl = "https://github.com/NVTechKorea/MessageEncryptor/raw/master/macOS/" + LatestVer
							+ "/MessageEncryptor.zip";
				} else {
					print("Downloading Universal...");
					sUrl = "https://github.com/NVTechKorea/MessageEncryptor/raw/master/Universal/" + LatestVer
							+ "/MessageEncryptor.jar";
				}
				if (OSKind.startsWith("windows") || OSKind.startsWith("mac")) {
					path = osdata[2] + osdata[0] + "Downloads" + osdata[0] + "MessageEncryptor_" + LatestVer
							+ "_Update.zip";
				} else {
					path = osdata[2] + osdata[0] + "Downloads" + osdata[0] + "MessageEncryptor_" + LatestVer
							+ "_Update.jar";
				}
				dlhelper.initiate(sUrl, path, "updateServer");
				lblDownloading.setVisible(false);
				dialogBox("Update download finished.\nPlease launch the downloaded software.", "Update Tool");
				System.exit(0);
			} else {
				if (!atLaunch) {
					dialogBox("You are using the latest version.", "Update Helper");
				}
				DeleteFile df = new DeleteFile();
				path = osdata[2] + osdata[0] + programManu + osdata[0] + programName + osdata[0] + ver + osdata[0]
						+ "storage" + osdata[0] + "Latest.signdoc";
				df.initiate(path, true);
				lblDownloading.setVisible(false);
			}
		}
	}

	public void dialogBox(String Msg, String title) {
		String informationString = Msg;
		JOptionPane.showMessageDialog(null, informationString, title, JOptionPane.PLAIN_MESSAGE);
		System.out.println("GUIMSG [CORE]: " + Msg);
	}

	public void info() {
		String informationString = "MessageEncryptor Version: " + ver
				+ "\nDreamLauncher version: 0.9.5 for MessageEncryptor\nCopyright (C) DreamProjectGroup 2017 - 2018";
		JOptionPane.showMessageDialog(null, informationString, "About This Application", JOptionPane.PLAIN_MESSAGE);
	}

	public void print(String s) {
		System.out.println("INFO [CORE]: " + s);
	}

	public void error(String s) {
		System.out.println("ERROR [CORE]: " + s);
	}
}

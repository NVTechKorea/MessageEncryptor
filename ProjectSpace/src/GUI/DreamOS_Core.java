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
import Engines.TCEngine;
import Engines.DownloadHelper;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class DreamOS_Core extends JFrame {
	private static final long serialVersionUID = 1L;
	private final String ver = "1.7";
	private final double vers = 1.7;
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
	private JButton btnPref;

	private final String headerMain = "oFuxbpSV1gga5pMhOJ9P7w==";
	private final String splitter = "wiSiAlNOwEh8UoBQhLVkuA==";
	private final String header = headerMain + splitter;
	private String prefpath = null;

	private boolean endecryptLegacy = false;
	private boolean chkupdatelaunch = true;
	private boolean blockupdate = false;
	
	private int count = 0;
	
	TCEngine ne = null;
	public void initiate(String rspath) {
		prefpath = rspath;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DreamOS_Core frame = new DreamOS_Core(rspath);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void generateGraphic() {
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
		
		btnPref = new JButton("Option");
		btnPref.setBounds(316, 372, 128, 29);
		contentPane.add(btnPref);
	}

	public DreamOS_Core(String rspath) {
		prefpath = rspath;
		generateGraphic();
		count = count + 1;
		ne = new TCEngine();
		print("Object creation process finished.");
		print("Loading preferences...");
		loadPref();
		checkForUpdateAtLaunch();
	}
	public String loadPref() {
		ReadFile rf = new ReadFile();
		String tempPref = rf.initiate(prefpath);
		String parse[] = tempPref.split(" -");
		if(parse[1].equals("checkUpdateAtLaunch false")) {
			chkupdatelaunch = false;
		}else {
			chkupdatelaunch = true;
		}
		if(parse[3].equals("blockUpdate true")) {
			blockupdate = true;
		}else {
			blockupdate = false;
		}
		return tempPref;
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
	public void checkForUpdateAtLaunch() {
		if(chkupdatelaunch) {
			update(true);
		}
		buttonListener();
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
		btnPref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disableAllMsgCode();
				readPreferences();
			}
		});
	}
	public void readPreferences() {
		String option = PassKey.getText();
		if(option.contains("/apply")) {
			if(option.contains(" -")) {
				String parsable[] = option.split(" -");
				if(parsable.length==4) {
					WriteFile wf = new WriteFile();
					String newOption[] = option.split("apply");
					wf.initiate(prefpath, newOption[1]);
					loadPref();
					print(newOption[1]);
					dialogBox("Preferences applied.", "Preference Installer");
				}else {
					print("Arguments are too short or too long!");
				}
			}else {
				
			}
		}else {
			String show = loadPref();
			MsgResult.setText(show);
		}
	}
	public void calculationEngine(boolean encrypt) {
		String finalMessage = null;
		String content = MsgToConvert.getText();
		String pass = PassKey.getText();
		if(content.equals("")||pass.equals("")) {
			showWarning("One of the content fields are empty.\nPlease type contents in Passkey and Message To Convert.");
		}else {
			if(encrypt) {
				finalMessage = ne.encrypt(content, pass);
				String addData = finalMessage + "WrittenVersion:" + ver + "<data>";
				finalMessage = addData;
				MsgResult.setText(finalMessage);
				lblEncryptSuccess.setVisible(true);
			}else {
				finalMessage = ne.decrypt(content, pass);
				if(finalMessage.equals("SYSTEM_return:noHeaderData")) {
					finalMessage = "";
					lblNoHeader.setVisible(true);
				}else if(finalMessage.equals("SYSTEM_return:wrongPasswordData")) {
					finalMessage = "";
					lblWrongPasscode.setVisible(true);
				}else if(finalMessage.equals("SYSTEM_return:brokenData")) {
					finalMessage = "";
					lblNoHeader.setVisible(true);
				}else {
					lblDecryptSuccess.setVisible(true);
					MsgResult.setText(finalMessage);
				}
			}
			
		}
	}

	public void update(boolean atLaunch) {
		if(!blockupdate) {
			lblDownloading.setVisible(true);
			print("Running update tool...");
			print("Getting information...");
			boolean downgrade = false;
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
				double fServer = Double.parseDouble(LatestVer);
				if(fServer<vers) {
					downgrade = true;
					dialogBox("Current version has a problem and needed to be\ndowngraded to the latest signed version.","Update Helper");
				}else {
					downgrade = false;
				}
				if (!LatestVer.equals(vers + "")) {
					boolean yes = true;
					if(!downgrade) {
						yes = ynDBox("Update found: " + LatestVer + "\nCurrent version: " + vers
								+ "\nWould you like to update?", "Update found");
					}else {
						yes = true;
					}
					if(yes) {
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
					}else {
						disableAllMsgCode();
					}
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
		}else {
			if(!atLaunch) {
				dialogBox("Update is blocked in preference.", "Update Helper");
			}	
		}
	}

	public void dialogBox(String Msg, String title) {
		String informationString = Msg;
		JOptionPane.showMessageDialog(null, informationString, title, JOptionPane.PLAIN_MESSAGE);
		System.out.println("GUIMSG [CORE]: " + Msg);
	}
	
	public boolean ynDBox(String msg, String title) {
		boolean yes = false;
		int dialogResult = JOptionPane.showConfirmDialog (null, msg,title, 0);
		if(dialogResult == JOptionPane.YES_OPTION){
			yes = true;
		}else {
			yes = false;
		}
		return yes;
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

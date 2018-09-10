package DreamOS;

//Declaration:
//DreamOS Launch Platform 0.9.5 For MessageEncryptor
//Copyright (C) Dream Project Group
import java.io.*;
import java.util.Scanner;

import Engines.PostSignatureCheck_New;
import Engines.SignatureCheck_New;

public class LibraryManager {
	String topPathPub = null;
	String storage = null;
	String resources = null;
	String splitter = null;
	String finalTopPath = null;
	String variables = null;
	String nameOfOS = null;
	String userDir = null;
	String process = null;
	String manu = null;
	String progn = null;
	String progv = null;
	String prefPath = null;
	boolean chkSig = true;
	public LibraryManager() {
	}

	public void genAllDir() {
		File manuf = new File(manu);
		File name = new File(progn);
		File vers = new File(progv);
		File stor = new File(storage);
		File resr = new File(resources);
		File var = new File(variables);
		if (!manuf.exists()) {
			manuf.mkdir();
		}
		if (!name.exists()) {
			name.mkdir();
		}
		if (!vers.exists()) {
			vers.mkdir();
		}
		if (!stor.exists()) {
			stor.mkdir();
		}
		if (!resr.exists()) {
			resr.mkdir();
		}
		if (!var.exists()) {
			var.mkdir();
		}
	}

	public String initiate(String top, String[] tops) {
		getOSNameAndPath();
		manu = userDir + splitter + tops[0];
		progn = manu + splitter + tops[1];
		progv = progn + splitter +tops[2];
		topPathPub = top;
		storage = topPathPub + "storage" + splitter;
		resources = topPathPub + "resources";
		variables = topPathPub + "var";
		genAllDir();
		checkFileExistance();
		firstRunMgr();
		return prefPath;
	}

	public void setVariable() {
		storage = topPathPub + "storage" + splitter;
		resources = topPathPub + "resources";
		variables = topPathPub + "var";
	}

	public void firstRunMgr() {
		try {
			process = "Entered First Run Manager";
			prefPath = resources + splitter + "preferences.mldy";
			File firstrun = new File(variables + splitter + "firstRunFinished");
			File prefFile = new File(prefPath);
			WriteFile writer = new WriteFile();
			if(!prefFile.exists()) {
				writer.initiate(prefPath, " -checkUpdateAtLaunch true -checkSignature true -blockUpdate false");
			}
			if (!firstrun.exists()) {
				process = "Writing: First Run Flag";
				String temp = variables + splitter + "firstRunFinished";
				writer.initiate(temp, " ");
				writer.initiate(resources + splitter + "preferences.mldy", " -checkUpdateAtLaunch true -checkSignature true -blockUpdate false");
				print("Checking signature...");
				SignatureCheck_New sig = new SignatureCheck_New();
				sig.regvar(topPathPub + "storage" + splitter, variables + splitter + "firstRunFinished");
				sig.initiate();
			}else {
				ReadFile rf = new ReadFile();
				String temp = rf.initiate(resources + splitter + "preferences.mldy");
				String temp1[] = temp.split(" -");
				print("Length of arguments: " + temp1.length);
				print("Contents: " + temp);
				if(temp1[2].equals("checkSignature false")) {
					chkSig = false;
				}
				if(chkSig) {
					PostSignatureCheck_New psc = new PostSignatureCheck_New();
					psc.regvar(storage);
					psc.initiate();
				}
			}

		} catch (Exception e) {
			ErrorAnalyzer ea = new ErrorAnalyzer();
			ea.initiate(e, process, false);
			System.out.print("Exit? Y/N");
			Scanner input = new Scanner(System.in);
			String s = input.nextLine();
			s = s.toLowerCase();
			input.close();
			if (s.equals("y")) {
				System.exit(0);
			}

		}
	}

	public void checkFileExistance() {
		ErrorAnalyzer ea = new ErrorAnalyzer();
		if (topPathPub==null) {
			handleTopPathPubNull();
		} else {
			try {
				process = "Check file exists";
				File f_topPath = new File(topPathPub);
				File f_storage = new File(storage);
				File f_resources = new File(resources);
				File f_var = new File(variables);
				if (f_topPath.exists()) {
					print("Top Path already exists.");
					if (f_topPath.list().length > 0) {
					} else {
						print("Generating inner library!");
						try {
							process = "Generate inner library";
							f_storage.mkdir();
							f_resources.mkdir();
							f_var.mkdir();
						} catch (Exception e) {
							ea.initiate(e, process, false);
						}
					}
				} else {
					print("Generating total library!");
					try {
						process = "Generate total library";
						f_topPath.mkdir();
						f_storage.mkdir();
						f_resources.mkdir();
						f_var.mkdir();
						print("Done.");
					} catch (Exception e) {
						ea.initiate(e, process, false);
						error("It seems that the higher directory does not exists.");
					}
				}
			} catch (Exception e) {
				ea.initiate(e, process, false);
			}
		}
	}

	public void handleTopPathPubNull() {
		error("Directory configurations starts with null.");
		print("Please manually enter the Top Path.");
		Scanner input = new Scanner(System.in);
		topPathPub = input.nextLine();
		setVariable();
		checkFileExistance();
		input.close();
	}

	public void getOSNameAndPath() {
		OSReader osreader = new OSReader();
		String data[] = osreader.initiate();
		nameOfOS = data[1];
		userDir = data[2];
		splitter = data[0];
	}

	public static void error(String s) {
		System.out.println("ERROR [LIBMGR]: " + s);
	}

	public static void print(String s) {
		System.out.println("INFO [LIBMGR]: " + s);
	}

}
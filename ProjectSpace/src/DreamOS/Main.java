package DreamOS;
import GUI.*;
// Declaration:
// DreamOS Launch Platform 0.9.5 for DRMessageEncryptor
// Copyright (C) Dream Project Group
public class Main{
	final static String runtimeVer = "DreamOS Launch Platform 0.9.5 For MessageEncryptor";
	final static String programManu = "DreamProjectGroup";
	final static String programName = "DRMessageEncryptor";
	final static String programVers = "1.6";
	final static String programEdit = "null";
	static String nameOfOS = null;
	static String userDir = null;
	static String dirSplitter = null;
	static String filePath = null;
	public Main(){};
	public static void main(String[] args) {
		print("Started...");
		print(":::::::::::::::DECLARATION:::::::::::::::");
		print("Base runtime: " + runtimeVer);
		print("Program name: " + programName);
		print("Program version: " + programVers);
		print("Manufacturer: " + programManu);
		print("Edition: " + programEdit);
		print(":::::::::::::::::::::::::::::::::::::::::");
		print("Get OS information...");
		getOSNameAndPath();
		print("Launching libmgr...");
		String[] data = {programManu, programName, programVers};
		LibraryManager lm = new LibraryManager();
		lm.initiate(filePath, data);
		print("Launching core...");
		DreamOS_Core core = new DreamOS_Core();
		core.initiate();
	}
	public static void getOSNameAndPath(){
		OSReader osreader = new OSReader();
		String data[] = osreader.initiate();
		nameOfOS = data[1];
		userDir = data[2];
		dirSplitter = data[0];
		filePath = userDir + dirSplitter + programManu + dirSplitter + programName + dirSplitter + programVers + dirSplitter;
	}
	public static void error(String s){
		System.out.println("ERROR [MAIN]: " + s);
	}
	public static void print(String s){
		System.out.println("INFO [MAIN]: " + s);
	}
	public static void exit(int val){
		System.out.println("EXIT [MAIN]: Exiting program.");
		System.exit(val);
	}

}
package DreamOS;
//Declaration:
//DreamOS Launch Platform 0.9.5 For MessageEncryptor
//Copyright (C) Dream Project Group
public class ErrorAnalyzer{
	public ErrorAnalyzer(){}
	public void initiate(Exception e, String process, boolean debug){
		String er = e.toString();
		uiprint("Process: " + process);
		if(er.contains("NullPointerException")){
			uiprint("NullPointerException found.");
			uiprint("Please check all the variables are correct, or contact to software manufacturer.");
		}else if (er.contains("ArrayIndexOutOfBoundsException")) {
			uiprint("ArrayIndexOutOfBoundsException found.");
			uiprint("Please check whether you entered too many items.");
		}else if (er.contains("NoClassDefFoundError")) {
			uiprint("NoClassDefFoundError found.");
		}else if (er.contains("FileNotFoundException")) {
			uiprint("FileNotFoundException found.");
			uiprint("Please check all resource files are located correctly.");
		}else if (er.contains("ClassNotFoundException")) {
			uiprint("ClassNotFoundException found.");
			uiprint("Please check wheter you've renamed system file, or script is compiled.");
		}else{
			uiprint("Unknown error. Running printStackTrace only.");
		}
		if(true){
			uiprint("/////////////PANIC/////////////");
			e.printStackTrace();
			uiprint("///////////////////////////////");	
		}
		System.exit(0);
	}
	public static void uiprint(String s){
		System.out.println("INFO [E-ANALYZER]: " + s);
	}
}
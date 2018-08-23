package DreamOS;
//Declaration:
//DreamOS Launch Platform 0.9.5 For MessageEncryptor
//Copyright (C) Dream Project Group
public class OSReader{
	public OSReader(){}
	public String[] initiate(){
		String nameOfOS = System.getProperty("os.name");
		String userDir = System.getProperty("user.home");
		String dirSplitter = null;
		nameOfOS = nameOfOS.toLowerCase();
		if(nameOfOS.contains("mac")){
			dirSplitter = "/";
		}else if (nameOfOS.contains("windows")) {
			dirSplitter = "//";
		}else if (nameOfOS.contains("linux")) {
			dirSplitter = "/";
		}else{
			error("Unknown OS!");
			print("Trying with default splitter: /");
			dirSplitter = "/";
		}
		String cache = dirSplitter + ".split." + nameOfOS + ".split." + userDir;
		String[] data = cache.split(".split.");
		return data;
	}
	public static void error(String s){
		System.out.println("Error [OSReader]: " + s);
	}
	public static void print(String s){
		System.out.println("INFO [OSReader]: " + s);
	}
}
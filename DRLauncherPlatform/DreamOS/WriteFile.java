package DreamOS;
//Declaration:
//DreamOS Launch Platform 0.9.5 For MessageEncryptor
//Copyright (C) Dream Project Group
import java.io.*;
public class WriteFile{
	public WriteFile(){}
	public void initiate(String path, String contents){
		String process = "Writing file: " + path;
		Writer writer = null;
		try{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
			writer.write(contents);
			File file = new File(path);
			if(!file.exists()) {
				System.out.println("ERROR [WRITER]: File was not written.");
			}
		}catch(Exception e){
			System.out.println("ERROR [WRITER]: Writing failure.");
			ErrorAnalyzer ea = new ErrorAnalyzer();
			ea.initiate(e, process, false);
		}finally {
			try {
				writer.close();
			} catch (Exception ex) {
				System.out.println("Final error on writing handler");
			}
		}
	}
}
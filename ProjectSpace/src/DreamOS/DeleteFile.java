package DreamOS;
//Declaration:
//DreamOS Launch Platform 0.9.5 For MessageEncryptor
//Copyright (C) Dream Project Group
import java.io.File;
public class DeleteFile{
	public DeleteFile(){}
	public void initiate(String path, boolean silent){
		File file = new File(path);
		if(file.delete()){
			if(!silent){
				System.out.println("Successfully deleted: " + path);
			}
		}else{
			System.out.println("Deleting failure: " + path);
		}
	}
}
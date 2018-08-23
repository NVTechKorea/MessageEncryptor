package DreamOS;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class EncryptTool{
    private static SecretKeySpec secretKey ;
    private static byte[] key ;
	private static String decryptedString;
	private static String encryptedString;
	private static String noErr = "fine";
    public static void setKey(String myKey){
    	MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
	    	key = Arrays.copyOf(key, 16); // use only first 128 bit
	    	//System.out.println(new String(key,"UTF-8"));
		    secretKey = new SecretKeySpec(key, "AES");   
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static String getDecryptedString() {
		return decryptedString;
	}
	public static void setDecryptedString(String decryptedString) {
		EncryptTool.decryptedString = decryptedString;
	}
    public static String getEncryptedString() {
		return encryptedString;
	}
	public static void setEncryptedString(String encryptedString) {
		EncryptTool.encryptedString = encryptedString;
	}
	public static String encrypt(String strToEncrypt){
        try{
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            setEncryptedString(Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));
        }catch (Exception e){
            System.out.println("Error while encrypting: "+e.toString());
        }
        return null;
    }

    public static void decrypt(String strToDecrypt){
        try{
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            setDecryptedString(new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt))));
        }catch (Exception e) {
            System.out.println("Error while decrypting: "+e.toString());
            System.out.println("Perhaps your decrypting password isn't correct?");
            noErr = ":ERR::WRONG::PW";
        }
    }
    public static String aesEncrypt(String contents, String pw) {
    	 final String strToEncrypt = contents;
         final String strPssword = pw;
         setKey(strPssword);
         encrypt(strToEncrypt.trim());
         String encrypted = EncryptTool.getEncryptedString();
         return encrypted;
    }
    
    public static String aesDecrypt(String contents, String pw) {
    	noErr = "fine";
        final String strPssword = pw;
        setKey(strPssword);
        final String strToDecrypt = contents;
        decrypt(strToDecrypt.trim());
        String decrypted = EncryptTool.getDecryptedString();
        if(noErr.contains("fine")) {
        	return decrypted;
        }else {
        	return noErr;
        }
    }
}
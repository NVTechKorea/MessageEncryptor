package dream.security.msgEncryptor;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;
public class EncryptTool extends Object {

    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static String noErr = "fine";
    private static String decryptedString;
    private static String encryptedString;

    public static void setKey(String myKey) {
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

    public static String encrypt(String strToEncrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] passin = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
            setEncryptedString(Base64.encodeToString(passin, 0));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static void decrypt(String strToDecrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] passin = strToDecrypt.getBytes();
            EncryptTool.setDecryptedString(new String(cipher.doFinal(Base64.decode(passin, 0))));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
            noErr = "ERR:WRONG_PW";
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
        if (noErr.equals("fine")) {
            return decrypted;
        } else {
            return noErr;
        }
    }
}
package dream.security.msgEncryptor

import android.os.Build
import android.support.annotation.RequiresApi
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Arrays

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import android.util.Base64

class EncryptTool : Any() {
    companion object {

        private var secretKey: SecretKeySpec? = null
        private var key: ByteArray? = null
        private var noErr = "fine"
        var decryptedString: String? = null
        var encryptedString: String? = null

        @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
        fun setKey(myKey: String) {
            var sha: MessageDigest? = null
            try {
                sha = MessageDigest.getInstance("SHA-1")
                key = sha!!.digest(key)
                key = Arrays.copyOf(key!!, 16) // use only first 128 bit
                //System.out.println(new String(key,"UTF-8"));
                secretKey = SecretKeySpec(key, "AES")
            } catch (e: NoSuchAlgorithmException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: UnsupportedEncodingException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

        }

        fun encrypt(strToEncrypt: String): String? {
            try {
                val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
                cipher.init(Cipher.ENCRYPT_MODE, secretKey)
                val passin = cipher.doFinal(strToEncrypt.toByteArray(charset("UTF-8")))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                    encryptedString = Base64.encodeToString(passin, 0)
                }
            } catch (e: Exception) {

            }

            return null
        }

        fun decrypt(strToDecrypt: String) {
            try {
                val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
                cipher.init(Cipher.DECRYPT_MODE, secretKey)
                val passin = strToDecrypt.toByteArray()
                EncryptTool.decryptedString = String(cipher.doFinal(Base64.decode(passin, 0)))
            } catch (e: Exception) {
                noErr = "ERR:WRONG_PW"
            }

        }

        fun aesEncrypt(contents: String, pw: String): String? {
            setKey(pw)
            encrypt(contents.trim { it <= ' ' })
            return encryptedString
        }

        fun aesDecrypt(contents: String, pw: String): String? {
            noErr = "fine"
            setKey(pw)
            decrypt(contents.trim { it <= ' ' })
            val decrypted = EncryptTool.decryptedString
            return if (noErr == "fine") {
                decrypted
            } else {
                noErr
            }
        }
    }
}
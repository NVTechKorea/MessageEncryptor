package dream.security.msgEncryptor

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class DefaultScreen : AppCompatActivity() {
    // These are the global variables
    internal lateinit var MsgBoxToConvert: EditText
    internal lateinit var MsgBoxToShowResult: EditText
    internal lateinit var PasscodeBox: EditText
    internal lateinit var ButtonEncrypt: Button
    internal lateinit var ButtonDecrypt: Button
    internal lateinit var result: String
    internal lateinit var Passcode: String
    internal lateinit var Convert: String
    private val headerMain = "oFuxbpSV1gga5pMhOJ9P7w=="
    private val splitter = "wiSiAlNOwEh8UoBQhLVkuA=="
    private val header = headerMain + splitter
    internal var encrypt: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        initNotifier()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_screen)
        MsgBoxToConvert = findViewById(R.id.MsgToConvert)
        MsgBoxToShowResult = findViewById(R.id.MsgToShow)
        PasscodeBox = findViewById(R.id.PassCode)
        ButtonDecrypt = findViewById(R.id.btnDecrypt)
        ButtonEncrypt = findViewById(R.id.btnEncrypt)
        ButtonEncrypt.setOnClickListener {
            print("Encrypt button clicked.")
            encryptButtonAction()
        }
        ButtonDecrypt.setOnClickListener {
            print("Decrypt button clicked.")
            decryptButtonAction()
        }
    }

    fun initNotifier() {
        print("Core initiated.")
    }

    fun readInput() {
        Convert = MsgBoxToConvert.text.toString()
        Passcode = PasscodeBox.text.toString()
    }

    fun calculate(): String {
        var result: String
        val ect = EncryptTool()
        if (encrypt) {
            result = aesEncrypt(Convert, Passcode).toString()
            result = header + result
            showMsg("Encryption complete.")
        } else {
            if (!Convert!!.startsWith(header)) {
                showWarning("No header found.")
                result = " "
            } else {
                val parse = Convert!!.split(splitter.toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                Convert = parse[1]
                result = aesDecrypt(Convert, Passcode).toString()
                if (result == "ERR:WRONG_PW") {
                    showWarning("Wrong password")
                    result = " "
                } else {
                    showMsg("Decryption complete.")
                }
            }
        }
        return result
    }

    fun showOutput(result: String) {
        MsgBoxToShowResult.setText(result)
    }

    fun checkRequiredFields(): Boolean {
        var allFilled = false
        val field1 = MsgBoxToConvert.text.toString()
        val field3 = PasscodeBox.text.toString()
        if (field1 == null || field3 == null) {
            showWarning("Require field is not completed or result box is filled.")
            allFilled = false
        } else {
            allFilled = true
        }
        return allFilled
    }

    fun encryptButtonAction() {
        encrypt = true
        val checked = checkRequiredFields()
        if (checked) {
            readInput()
            val result = calculate()
            showOutput(result)

        }
    }

    fun decryptButtonAction() {
        encrypt = false
        val checked = checkRequiredFields()
        if (checked) {
            readInput()
            val result = calculate()
            showOutput(result)

        }
    }

    fun showWarning(warningMsg: String) {
        Toast.makeText(this, "ERROR: $warningMsg", Toast.LENGTH_SHORT).show()
        error(warningMsg)
    }

    fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        print(msg)
    }

    fun print(s: String) {
        println("INFO [CORE]: $s")
    }

    fun error(s: String) {
        println("ERROR [CORE]: $s")
    }
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

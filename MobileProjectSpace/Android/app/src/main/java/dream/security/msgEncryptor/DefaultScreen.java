package dream.security.msgEncryptor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DefaultScreen extends AppCompatActivity {
    // These are the global variables
    EditText MsgBoxToConvert, MsgBoxToShowResult, PasscodeBox;
    Button ButtonEncrypt, ButtonDecrypt;
    String result = null;
    String Passcode = null;
    String Convert = null;
    private String headerMain = "oFuxbpSV1gga5pMhOJ9P7w==";
    private String splitter = "wiSiAlNOwEh8UoBQhLVkuA==";
    private String header = headerMain + splitter ;
    boolean encrypt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initNotifier();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_screen);
        MsgBoxToConvert  = findViewById(R.id.MsgToConvert);
        MsgBoxToShowResult = findViewById(R.id.MsgToShow);
        PasscodeBox = findViewById(R.id.PassCode);
        ButtonDecrypt = findViewById(R.id.btnDecrypt);
        ButtonEncrypt = findViewById(R.id.btnEncrypt);
        ButtonEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                print("Encrypt button clicked.");
                encryptButtonAction();
            }
        });
        ButtonDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                print("Decrypt button clicked.");
                decryptButtonAction();
            }
        });
    }
    public void initNotifier() {
        print("Core initiated.");
    }
    public void readInput() {
        Convert = MsgBoxToConvert.getText().toString();
        Passcode = PasscodeBox.getText().toString();
    }
    public String calculate() {
        String result;
        EncryptTool ect = new EncryptTool();
        if(encrypt) {
            result = ect.aesEncrypt(Convert, Passcode);
            result = header + result;
            showMsg("Encryption complete.");
        }else{
            if(!Convert.startsWith(header)) {
                showWarning("No header found.");
                result = " ";
            }else {
                String[] parse = Convert.split(splitter);
                Convert = parse[1];
                result = ect.aesDecrypt(Convert, Passcode);
                if(result.equals("ERR:WRONG_PW")){
                    showWarning("Wrong password");
                    result = " ";
                }else {
                    showMsg("Decryption complete.");
                }
            }
        }
        return result;
    }
    public void showOutput(String result) {
        MsgBoxToShowResult.setText(result);
    }
    public boolean checkRequiredFields() {
        boolean allFilled = false;
        String field1 = MsgBoxToConvert.getText().toString();
        String field3 = PasscodeBox.getText().toString();
        if(field1==null||field3==null) {
            showWarning("Require field is not completed or result box is filled.");
            allFilled = false;
        }else {
            allFilled = true;
        }
        return allFilled;
    }
    public void encryptButtonAction() {
        encrypt = true;
        boolean checked = checkRequiredFields();
        if(checked) {
            readInput();
            String result = calculate();
            showOutput(result);

        }
    }
    public void decryptButtonAction() {
        encrypt = false;
        boolean checked = checkRequiredFields();
        if(checked) {
            readInput();
            String result = calculate();
            showOutput(result);

        }
    }

    public void showWarning(String warningMsg) {
        Toast.makeText(this, "ERROR: " + warningMsg, Toast.LENGTH_SHORT).show();
        error(warningMsg);
    }
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        print(msg);
    }
    public void print(String s) {
        System.out.println("INFO [CORE]: " + s);
    }
    public void error(String s) {
        System.out.println("ERROR [CORE]: " + s);
    }
}

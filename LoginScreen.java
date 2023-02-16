package akaalwebsoft.com.gurudwara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

import akaalwebsoft.com.gurudwara.ViewModle.UserCredentialViewModel;
import akaalwebsoft.com.gurudwara.common.SharedPreference;
import akaalwebsoft.com.gurudwara.modle.ValidateUserCredentialModle;

public class LoginScreen extends AppCompatActivity {
    EditText contact, code, username, loginpassword, machineid;
    Button submit;
    TextView deviceid,versonname;
    UserCredentialViewModel userCredentialViewModel;
    SharedPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        preference = SharedPreference.getInstance(this);
        userCredentialViewModel = new ViewModelProvider(LoginScreen.this).get(UserCredentialViewModel.class);
        id();
        versonname.setText("© Akaal WebSoft Pvt. Ltd.  Date:31/10/2022,  [Ver:"+BuildConfig.VERSION_NAME+"]");
        getdata();
    }


    private void getdata() {
        //id
        String ID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        //authentication
        String name = "AKAAL";
        String password = "SnVuIDEzLCAyMDIy";
        String base = name + ":" + password;
        byte[] en = new byte[0];
        try {
            en = base.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = android.util.Base64.encodeToString(en, Base64.NO_WRAP);
        String auth = "Basic " + base64;
        deviceid.setText("Device ID : " + ID);
        preference.setDeviceid(ID);
        preference.setAuth(auth);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("emial", username.getText().toString());
                Log.e("pass", loginpassword.getText().toString());
                userCredentialViewModel.validateUserCredentialModleLiveData(LoginScreen.this, username.getText().toString(), loginpassword.getText().toString(), ID, auth).observe(LoginScreen.this, new Observer<ValidateUserCredentialModle>() {
                    @Override
                    public void onChanged(ValidateUserCredentialModle validateUserCredentialModle) {
                        if (validateUserCredentialModle.getStatuscode().equalsIgnoreCase("Success")) {
                            if (validateUserCredentialModle.getData().getIsValidUser().toString().equalsIgnoreCase("true")) {
                                preference.setAccessToken(validateUserCredentialModle.getData().getAccessToken());
                                preference.setGurudwaraname(validateUserCredentialModle.getData().getGurudwaraName());
                                preference.setGurudwaraAddress(validateUserCredentialModle.getData().getGurudwaraAddress());
                                Intent intent = new Intent(LoginScreen.this, ManagerCashierScreen.class);
                                preference.setAccessToken(validateUserCredentialModle.getData().getAccessToken());
//                                intent.putExtra("auth", auth);
//                                intent.putExtra("token", validateUserCredentialModle.getData().getAccessToken());
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginScreen.this, "Invalid User", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginScreen.this, "" + validateUserCredentialModle.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void id() {
        submit = findViewById(R.id.submit);
        username = findViewById(R.id.username);
        loginpassword = findViewById(R.id.loginpassword);
        deviceid = findViewById(R.id.deviceid);
        versonname = findViewById(R.id.versonname);
    }
}
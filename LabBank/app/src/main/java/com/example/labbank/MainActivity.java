package com.example.labbank;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView msg_txt = findViewById(R.id.txt_msg);
        Button login_btn = findViewById(R.id.login_btn);
        //We define the fingerprint and check the different cases
        BiometricManager biometricManager = BiometricManager.from(this);
        switch(biometricManager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_SUCCESS:
                msg_txt.setText("You can use the fingerprint sensor to login.");
                break;
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    msg_txt.setText("The device don't have a fingerprint sensor.");
                    login_btn.setVisibility(View.GONE);
                    break;
                    case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                        msg_txt.setText("The biometric sensor is currently unavailable.");
                        login_btn.setVisibility(View.GONE);
                        break;
                        case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                            msg_txt.setText("Your device don't have any fingerprint saved, please check your settings.");
                            login_btn.setVisibility(View.GONE);
                            break;

        }
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback(){
            @Override //Method called when there is an error while the authentication
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override //Method called when the authentication is a success
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Login success ! ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }

            @Override //Method called if we have failed the authentication
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setDescription("Use your fingerprint to login to your app.")
                .setNegativeButtonText("Cancel")
                .build();
        login_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }
}
package com.example.reportingsystemmobile.login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reportingsystemmobile.R;
import com.example.reportingsystemmobile.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button register;
    private LoginService loginService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginService = new LoginService(this);
        usernameEditText = findViewById(R.id.username_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        loginButton = findViewById(R.id.login_button);
        register = findViewById(R.id.registerAcc_button);

        loginButton.setOnClickListener(v -> {
            LoginData loginData = new LoginData();
            loginData.setUsername(usernameEditText.getText().toString());
            loginData.setPassword(passwordEditText.getText().toString());

            loginService.login(loginData);
        });

        register.setOnClickListener(v -> {
            changeActivity(new Intent(this, RegisterActivity.class));
        });
    }

    public void changeActivity(Intent intent) {
        startActivity(intent);
    }

    public void displayToast(String message) {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show());
    }


    // for checking internet connection
    public boolean connect() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        } else {
            return false;
        }
    }
}

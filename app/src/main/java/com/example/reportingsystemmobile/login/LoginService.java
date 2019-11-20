package com.example.reportingsystemmobile.login;

import android.content.Intent;

import com.example.reportingsystemmobile.RestServiceBuilder;
import com.example.reportingsystemmobile.menu.MenuActivity;

import java.io.IOException;

public class LoginService {

    private LoginActivity loginActivity;

    private LoginApiInterface loginApiInterface = RestServiceBuilder.getClient().create(LoginApiInterface.class);

    public LoginService(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public void login(LoginData loginData) {

        try {
            int response = loginApiInterface.login(loginData).execute().code();
            if (response == 200) {
                loginActivity.displayToast("Logged");
                loginActivity.changeActivity(new Intent(loginActivity.getApplicationContext(), MenuActivity.class));
            }
            if (response == 401) {
                loginActivity.displayToast("Failed Login attempt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

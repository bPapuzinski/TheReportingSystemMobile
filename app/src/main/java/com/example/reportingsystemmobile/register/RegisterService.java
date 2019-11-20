package com.example.reportingsystemmobile.register;

import android.content.Intent;

import com.example.reportingsystemmobile.RestServiceBuilder;
import com.example.reportingsystemmobile.login.LoginActivity;

import java.io.IOException;

public class RegisterService {

    private RegisterActivity registerActivity;

    private RegisterApiInterface registerApiInterface = RestServiceBuilder.getClient().create(RegisterApiInterface.class);

    public RegisterService(RegisterActivity registerActivity) {
        this.registerActivity = registerActivity;
    }

    public void register(RegisterData registerData) {

        try {
            RegisterResponse response = registerApiInterface.registerNewAccount(registerData).execute().body();
            if (response.getHttpCode() == 201) {
                registerActivity.displayToast("New account created");
                registerActivity.changeActivity(new Intent(registerActivity.getApplicationContext(), LoginActivity.class));
            }
            if (response.getHttpCode() == 400) {
                registerActivity.displayToast("Bad request");
            }
            if (response.getHttpCode() == 409) {
                if (response.getMessage().equals("Not unique Username")) {
                    registerActivity.displayToast("Not unique Username");
                }
                if (response.getMessage().equals("Not unique Email")) {
                    registerActivity.displayToast("Not unique Email");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

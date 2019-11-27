package com.example.reportingsystemmobile.login;

import android.content.Intent;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.example.reportingsystemmobile.RestServiceBuilder;
import com.example.reportingsystemmobile.menu.MenuActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {

    private LoginActivity loginActivity;

    private LoginApiInterface loginApiInterface;

    public LoginService(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        loginApiInterface = RestServiceBuilder.getClient(loginActivity.getApplicationContext()).create(LoginApiInterface.class);
    }

    public void login(LoginData loginData) {

        loginApiInterface.login(loginData).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() == 200) {
                    SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(loginActivity.getApplicationContext()).edit();
                    memes.putInt("USER_ID", response.body().getUserId()).apply();
                    memes.commit();
                    loginActivity.displayToast("Logged");
                    loginActivity.changeActivity(new Intent(loginActivity.getApplicationContext(), MenuActivity.class));
                }
                if (response.code() == 401) {
                    loginActivity.displayToast("Failed Login attempt");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginActivity.displayToast("Error while sending request");
            }
        });
    }
}

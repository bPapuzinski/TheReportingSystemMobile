package com.example.reportingsystemmobile.login;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.example.reportingsystemmobile.RestServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {

    private LoginFragment loginFragment;

    private LoginApiInterface loginApiInterface;

    public LoginService(LoginFragment loginFragment) {
        this.loginFragment = loginFragment;
        loginApiInterface = RestServiceBuilder.getClient(loginFragment.getContext()).create(LoginApiInterface.class);
    }

    public void login(LoginData loginData) {

        loginApiInterface.login(loginData).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() == 200) {
                    SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(loginFragment.getContext()).edit();
                    memes.putInt("USER_ID", response.body().getUserId()).apply();
                    memes.commit();
                    loginFragment.displayToast("Logged");
                    loginFragment.changeMenuContext();
                    loginFragment.changeUsernameInHeaderMenu(response.body().getUsername());
                    loginFragment.changeFragmentToReportList();
                }
                if (response.code() == 401) {
                    loginFragment.displayToast("Failed Login attempt");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginFragment.displayToast("Error while sending request");
            }
        });
    }
}

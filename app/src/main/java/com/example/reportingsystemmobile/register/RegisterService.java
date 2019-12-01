package com.example.reportingsystemmobile.register;

import com.example.reportingsystemmobile.RestServiceBuilder;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class RegisterService {

    private RegisterActivity registerActivity;

    private RegisterApiInterface registerApiInterface;

    public RegisterService(RegisterActivity registerActivity) {
        this.registerActivity = registerActivity;
        registerApiInterface = RestServiceBuilder.getClient(registerActivity.getContext()).create(RegisterApiInterface.class);
    }

    public void register(RegisterData registerData) {

        registerApiInterface.registerNewAccount(registerData).enqueue(new Callback<RegisterResponse>() {

            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse.getHttpCode() == 201) {
                        registerActivity.displayToast("New account created");
                        registerActivity.changeFragmentToLogin();
                    }
                } else {
                    Gson g = new Gson();
                    try {
                        RegisterResponse registerResponse1 = g.fromJson(response.errorBody().string(), RegisterResponse.class);

                        if (response.code() == 400) {
                            registerActivity.displayToast("Bad request");
                        }
                        if (response.code() == 409) {
                            if (registerResponse1.getMessage().equals("Not unique Username")) {
                                registerActivity.displayToast("Not unique Username");
                            }
                            if (registerResponse1.getMessage().equals("Not unique Email")) {
                                registerActivity.displayToast("Not unique Email");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                registerActivity.displayToast("Error while sending request");
            }
        });
    }

}

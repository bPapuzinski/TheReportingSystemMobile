package com.example.reportingsystemmobile.login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApiInterface {

    @POST("/login")
    Call<LoginResponse> login(@Body LoginData loginData);

}

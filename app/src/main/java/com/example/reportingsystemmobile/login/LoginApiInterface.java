package com.example.reportingsystemmobile.login;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApiInterface{

    @POST("/login")
    LoginResponse login(@Body LoginData loginData);

}

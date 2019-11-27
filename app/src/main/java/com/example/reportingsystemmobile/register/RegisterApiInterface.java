package com.example.reportingsystemmobile.register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterApiInterface {

    @POST("/register")
    Call<RegisterResponse> registerNewAccount(@Body RegisterData registerData);

}

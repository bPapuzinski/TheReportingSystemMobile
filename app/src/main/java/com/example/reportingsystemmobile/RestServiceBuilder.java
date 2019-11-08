package com.example.reportingsystemmobile;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestServiceBuilder {

    private static Retrofit retrofit = null;

    static Retrofit getClient() {

        OkHttpClient client = new OkHttpClient.Builder().build();

        String url = "http://localhost:8080";

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

}

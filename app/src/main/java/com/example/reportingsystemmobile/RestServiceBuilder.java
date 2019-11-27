package com.example.reportingsystemmobile;

import android.content.Context;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestServiceBuilder {

    private static Retrofit retrofit = null;


    public static Retrofit getClient(Context context) {


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor(context))
                .addInterceptor(new ReceivedCookiesInterceptor(context))
                .addInterceptor(logging)
                .build();

        String url = "http://192.168.2.10:8080";
        String urlProd = "https://reportingsystembackend.herokuapp.com";

        retrofit = new Retrofit.Builder()
                .baseUrl(urlProd)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}

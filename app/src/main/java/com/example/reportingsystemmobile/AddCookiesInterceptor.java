package com.example.reportingsystemmobile;

import android.content.Context;
import android.preference.PreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    public static final String PREF_COOKIES = "AUTHORIZATION";
    private Context context;

    public AddCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        String preferences = PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_COOKIES, "");

        builder.addHeader("Authorization", preferences);

        return chain.proceed(builder.build());
    }
}

package com.example.edutask.RestConnection;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseUrl {

    public static final String BASE_URL = "https://www.dropbox.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context mContext) {

        if (retrofit == null){
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(new NetworkConnectionInterceptor(mContext))
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}

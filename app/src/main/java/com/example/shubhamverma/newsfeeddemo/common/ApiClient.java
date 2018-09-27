package com.example.shubhamverma.newsfeeddemo.common;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(WebserviceUrl.getNewsData)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build();
        }

return retrofit;
    }

    private static OkHttpClient getHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(Constants.requestTimeOutInSeconds, TimeUnit.SECONDS)
                .writeTimeout(Constants.requestTimeOutInSeconds, TimeUnit.SECONDS)
                .connectTimeout(Constants.requestTimeOutInSeconds, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

}

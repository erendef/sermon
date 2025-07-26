package com.erencol.sermon.Data.Service;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SermonClient {
    private static SermonClient instance;

    public static ISermons create() {
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(null)
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .header("Cache-Control", "no-cache")
                            .header("Pragma", "no-cache")
                            .build();
                    return chain.proceed(request);
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Host.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(ISermons.class);
    }

    public static SermonClient getInstance(){
        if (instance == null)
            instance = new SermonClient();
        return instance;
    }


}

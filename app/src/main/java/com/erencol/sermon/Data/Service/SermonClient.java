package com.erencol.sermon.Data.Service;

import com.erencol.sermon.Model.Sermon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SermonClient {
    private static SermonClient instance;
    public String url;

    public static ISermons create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Host.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(ISermons.class);
    }

    public static SermonClient getInstance(){
        if (instance == null)
            instance = new SermonClient();
        return instance;
    }


}

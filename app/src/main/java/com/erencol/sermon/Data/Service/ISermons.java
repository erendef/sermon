package com.erencol.sermon.Data.Service;

import com.erencol.sermon.Model.Sermon;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ISermons  {
    @GET Observable<List<Sermon>> getSermons(@Url String url);
}

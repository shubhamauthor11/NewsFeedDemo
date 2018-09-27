package com.example.shubhamverma.newsfeeddemo.common;

import com.example.shubhamverma.newsfeeddemo.requestModel.NewsResponseModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @GET
    Call<NewsResponseModel> getNewsResponse(@Url String url);


}

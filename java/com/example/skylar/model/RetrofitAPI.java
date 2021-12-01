package com.example.skylar.model;

import com.example.skylar.model.ContentModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {
    @GET
    Call<ContentModel> getAllContent(@Url String url);

    @GET
    Call<ContentModel> getContentByCategory(@Url String url);

}

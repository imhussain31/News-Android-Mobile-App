package com.mhndroid.newsapp.Service.Network;

import com.mhndroid.newsapp.Service.Model.NewsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HackerNewsApi {
    @GET("/v0/topstories.json")
    Call<List<Integer>> getTopStories();

    @GET("/v0/item/{itemId}.json")
    Call<NewsModel> getStory(@Path("itemId") int itemId);
}

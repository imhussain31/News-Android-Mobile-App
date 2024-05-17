package com.mhndroid.newsapp.Service.Network;

import com.mhndroid.newsapp.Service.Model.NewsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HackerNewsApi {
    @GET("topstories.json?print=pretty")
    Call<List<Integer>> getTopStories();

    @GET("newstories.json?print=pretty")
    Call<List<Integer>> getNewStories();

    @GET("item/{id}.json?print=pretty")
    Call<NewsModel> getStory(@Path("id") int id);



}

package com.mhndroid.newsapp.View.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mhndroid.newsapp.R;
import com.mhndroid.newsapp.Service.Model.NewsModel;
import com.mhndroid.newsapp.Service.Network.ApiClient;
import com.mhndroid.newsapp.Service.Network.HackerNewsApi;
import com.mhndroid.newsapp.View.Adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListActivity extends AppCompatActivity {

    RecyclerView newsListRecyclerView;
    NewsAdapter newsAdapter;

    String getNews;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        newsListRecyclerView = findViewById(R.id.newsListRecyclerViewId);
        newsListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<NewsModel> stories = new ArrayList<>();
        newsAdapter = new NewsAdapter(stories);
        newsListRecyclerView.setAdapter(newsAdapter);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null) {
            getNews = bundle.getString("key");
        }

        if (getNews.contains("topNews")){
            fetchTopNews();
        }else if (getNews.contains("latestNews")){
            fetchNewNews();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.setCancelable(false);
        progressDialog.show();


    }


    private void fetchTopNews() {
        HackerNewsApi api = ApiClient.getHackerNewsApi();

        api.getTopStories().enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    fetchStoryDetails(response.body(), "Top");
                } else {
                    progressDialog.dismiss();
                    Log.e("MainActivity", "Failed to fetch top stories");
                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });
    }

    private void fetchNewNews(){
        HackerNewsApi api = ApiClient.getHackerNewsApi();

        api.getNewStories().enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    fetchStoryDetails(response.body(), "New");
                } else {
                    progressDialog.dismiss();
                    Log.e("MainActivity", "Failed to fetch new stories");
                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });
    }

    private void fetchStoryDetails(List<Integer> storyIds, String storyType) {
        HackerNewsApi api = ApiClient.getHackerNewsApi();

        for (int i = 0; i < Math.min(storyIds.size(), 10); i++) { // Limit to 10 stories for demonstration
            int storyId = storyIds.get(i);
            api.getStory(storyId).enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        newsAdapter.addNews(List.of(response.body()));
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    } else {
                        progressDialog.dismiss();
                        Log.e("MainActivity", "Failed to fetch story details for " + storyType + " stories");
                    }
                }

                @Override
                public void onFailure(Call<NewsModel> call, Throwable t) {
                    progressDialog.dismiss();
                    Log.e("MainActivity", "Error: " + t.getMessage());
                }
            });
        }
    }

}
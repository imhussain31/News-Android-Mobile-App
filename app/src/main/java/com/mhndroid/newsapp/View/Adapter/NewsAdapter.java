package com.mhndroid.newsapp.View.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mhndroid.newsapp.R;
import com.mhndroid.newsapp.Service.Model.NewsModel;
import com.mhndroid.newsapp.View.UI.NewsDetailActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsModel> all_news;

    public NewsAdapter(List<NewsModel> stories) {
        this.all_news = stories;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_single_row_design, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        NewsModel news = all_news.get(position);
        holder.bind(news);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), NewsDetailActivity.class);
            intent.putExtra("title", news.getTitle());
            intent.putExtra("author", news.getBy());
            intent.putExtra("time", news.getTime());
            intent.putExtra("content", news.getUrl()); // Assuming content is in the URL, adjust if needed
            intent.putExtra("imageUrl", news.getUrl());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return all_news.size();
    }

    public void addNews(List<NewsModel> newStories) {
        int start = all_news.size();
        all_news.addAll(newStories);
        notifyItemRangeInserted(start, newStories.size());
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView newsTitle , authorName , publicationDate;

        private ImageView newsImageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            newsTitle = itemView.findViewById(R.id.titleTextViewId);
            authorName = itemView.findViewById(R.id.authorTextViewId);
            publicationDate = itemView.findViewById(R.id.publicationDateTextViewId);
            newsImageView = itemView.findViewById(R.id.newsImgId);

        }

        public void bind(NewsModel news) {
            newsTitle.setText(news.getTitle());
            authorName.setText(" By : "+news.getBy()+" ");
            String formattedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date(news.getTime() * 1000));
            publicationDate.setText(formattedDate);

            Glide.with(newsImageView.getContext())
                    .load(news.getUrl())
                    .placeholder(R.drawable.news)
                    .into(newsImageView);
        }
    }
}

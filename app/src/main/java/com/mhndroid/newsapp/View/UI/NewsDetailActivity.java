package com.mhndroid.newsapp.View.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mhndroid.newsapp.R;

public class NewsDetailActivity extends AppCompatActivity {
    private ImageView newsImageView;
    private TextView titleTextView;
    private TextView authorTextView;
    private TextView dateTextView;
    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        newsImageView = findViewById(R.id.newsImageView);
        titleTextView = findViewById(R.id.titleTextView);
        authorTextView = findViewById(R.id.authorTextView);
        dateTextView = findViewById(R.id.dateTextView);
        contentTextView = findViewById(R.id.contentTextView);

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String author = intent.getStringExtra("author");
            long time = intent.getLongExtra("time", 0);
            String content = intent.getStringExtra("content");
            String imageUrl = intent.getStringExtra("imageUrl");

            getSupportActionBar().setTitle(title);


            titleTextView.setText(title);
            authorTextView.setText(author);
            dateTextView.setText(new java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(new java.util.Date(time * 1000)));
            contentTextView.setText(content);
            Linkify.addLinks(contentTextView, Linkify.WEB_URLS);
            contentTextView.setMovementMethod(LinkMovementMethod.getInstance());

            contentTextView.setOnClickListener(v -> {
                String url = contentTextView.getText().toString();
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    Intent webIntent = new Intent(NewsDetailActivity.this, WebViewActivity.class);
                    webIntent.putExtra("url", url);
                    startActivity(webIntent);
                }
            });

            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.newspaper)
                    .into(newsImageView);
        }
    }
}
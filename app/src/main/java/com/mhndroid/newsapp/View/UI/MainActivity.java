package com.mhndroid.newsapp.View.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mhndroid.newsapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button topNewsBtn , latestNewsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        topNewsBtn = findViewById(R.id.topNewsBtnId);
        latestNewsBtn = findViewById(R.id.latestNewsBtnId);

        topNewsBtn.setOnClickListener(this);
        latestNewsBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.topNewsBtnId){
            Intent intent = new Intent(getApplicationContext() , NewsListActivity.class);
            intent.putExtra("key" , "topNews");
            startActivity(intent);
        }else if (view.getId() == R.id.latestNewsBtnId){
            Intent intent = new Intent(getApplicationContext() , NewsListActivity.class);
            intent.putExtra("key" , "latestNews");
            startActivity(intent);
        }
    }
}
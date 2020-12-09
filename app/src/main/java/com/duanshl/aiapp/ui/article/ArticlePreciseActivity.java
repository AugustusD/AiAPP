package com.duanshl.aiapp.ui.article;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.duanshl.aiapp.R;

public class ArticlePreciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_precise);
        WebView wv = findViewById(R.id.article_webview);
        //取得启动该Activity的Intent对象
        Intent intent =getIntent();
        /*取出Intent中附加的数据*/
        String content = intent.getStringExtra("content");
        wv.getSettings().setDefaultTextEncodingName("utf-8");

        wv.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);

    }
}
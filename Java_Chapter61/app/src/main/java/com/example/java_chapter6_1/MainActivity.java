package com.example.java_chapter6_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button namuwikiButton = findViewById(R.id.namuwikiButton);
        Button europa_cafeButton = findViewById(R.id.europa_cafeButton);
        Button weatherButton = findViewById(R.id.weatherButton);
        Button mapButton = findViewById(R.id.mapButton);
        Button backButton = findViewById(R.id.backButton);
        Button reloadButton = findViewById(R.id.reloadButton);
        Button goButton = findViewById(R.id.goButton);

        final WebView webview = findViewById(R.id.webview);

        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://m.naver.com");

        namuwikiButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                webview.loadUrl("https://namu.wiki");
            }
        });

        europa_cafeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                webview.loadUrl("http://cafe.daum.net/Europa");
            }
        });

        weatherButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        mapButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                webview.goBack();
            }
        });

        reloadButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                webview.reload();
            }
        });

        goButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                webview.goForward();
            }
        });
    }
}

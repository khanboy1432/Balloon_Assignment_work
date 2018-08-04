package com.khanboy.balloon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebsiteActivity extends AppCompatActivity {

    // TODO: Declare member variables here

    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);


        // TODO: Load website in webview

        mWebView = (WebView) findViewById(R.id.current_website);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);

        mWebView.loadUrl(getIntent().getStringExtra("websiteurl"));

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

               // Loading website inside webview

                view.loadUrl(url);
                return true;
            }
        });


    }

    // TODO: Navigate to MainActivity

    @Override
    public void onBackPressed() {


            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
              finish();
            return;


    }

}

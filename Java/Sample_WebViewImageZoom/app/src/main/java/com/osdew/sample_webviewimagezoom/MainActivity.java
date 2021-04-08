package com.osdew.sample_webviewimagezoom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.webView);

        mWebView.loadUrl("https://www.osdew.com/app/api/TestImageZoom/ZoomTestPage.html");

        // 웹뷰 줌 기능 설정
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setSupportZoom(true);

        // 웹뷰 줌 컨트롤러 제거
        mWebView.getSettings().setDisplayZoomControls(false);

    }
}
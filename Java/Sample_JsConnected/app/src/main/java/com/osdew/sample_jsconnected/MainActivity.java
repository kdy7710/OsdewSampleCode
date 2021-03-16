package com.osdew.sample_jsconnected;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private Button button;
    public Context mContext;

    // 자바스크립트에서 연결하여 사용할 네이티브 함수 정의 인터페이스
    public interface CustomJavaScriptCallback{
        void webViewToApp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.webView);
        button = findViewById(R.id.button);

        mWebView.loadUrl("https://www.osdew.com/app/api/Sample_JS_DuplexCommunication.html");
        mWebView.setWebChromeClient(new WebChromeClient());

        // 웹뷰 설정 메소드 호출
        webViewInit(mWebView);

        mContext = this.getApplicationContext();

        // 네이티브에서 자바스크립트 함수 호출
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:changeColor()");
            }
        });

        // 자바스크립트에서 네이티브 함수 호출
        mWebView.addJavascriptInterface(new CustomJavaScriptCallback() {

            @JavascriptInterface
            @Override
            public void webViewToApp() {
                Intent intent = new Intent(mContext.getApplicationContext(), SubActivity.class);
                startActivity(intent);
            }
        }, "WebViewCallbackInterface");


    }

    public void webViewInit(WebView mWebView) {
        WebSettings settings = mWebView.getSettings();

        // Javascript 사용하기
        settings.setJavaScriptEnabled(true);

        // Text Encoding 이름 정의
        settings.setDefaultTextEncodingName("UTF-8");
    }
}
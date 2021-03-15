package com.osdew.sample_js_duplexcommunication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    public Context mContext;

    //자바스크립트에서 연결하여 사용할 네이티브 함수 정의 인터페이스
    public interface CustomJavaScriptCallback {
        void webViewToApp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this.getApplicationContext();

        // 웹뷰 URL 연결
        mWebView = findViewById(R.id.webView);
        mWebView.loadUrl("https://www.osdew.com/app/api/Sample_JS_DuplexCommunication.html");
        //mWebView.loadUrl("https://www.osdew.com/app/api/AutoLogin.html");
        mWebView.setWebChromeClient(new WebChromeClient());

        // 웹뷰 설정 메소드 호출
        webviewInit(mWebView);


        // 자바스크립트에서 네이트브 함수 호출
        mWebView.addJavascriptInterface(new CustomJavaScriptCallback() {

            @JavascriptInterface
            @Override
            public void webViewToApp() {
                Intent intent = new Intent(mContext.getApplicationContext(), SubActivity.class);
                startActivity(intent);
            }
        }, "WebViewCallbackInterface");


        // 네이티브에서 자바스크립트 함수 호출
        findViewById(R.id.button).setOnClickListener(
                view -> mWebView.evaluateJavascript("javascript:changeColor();", new ValueCallback<String>(){
                    @Override
                    public void onReceiveValue(String value) {
                        Log.w("DEBUG", "자바스크립트 함수 리턴 : " + value);
                    }
         }));
    }

    public void webviewInit(WebView mWebView) {
        WebSettings settings = mWebView.getSettings();
        // Javascript 사용하기
        settings.setJavaScriptEnabled(true);
        // WebView 내장 줌 사용여부
        settings.setBuiltInZoomControls(true);
        // 화면에 맞게 WebView 사이즈를 정의
        settings.setLoadWithOverviewMode(true);
        // ViewPort meta tag를 활성화 여부
        settings.setUseWideViewPort(true);
        // 줌 컨트롤 사용 여부
        settings.setDisplayZoomControls(false);
        // 사용자 제스처를 통한 줌 기능 활성화 여부
        settings.setSupportZoom(false);
        // TextEncoding 이름 정의
        settings.setDefaultTextEncodingName("UTF-8");

        // Setting Local Storage
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);

        // 캐쉬 사용 방법을 정의
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }




}

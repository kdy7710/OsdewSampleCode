package com.osdew.sample_webviewgobackendclose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView; // 웹뷰 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.webView);
        mWebView.loadUrl("https://www.osdew.com/app/api/sample_webViewHistoryBack/firstPage.html");

        //웹뷰 설정
        webviewInit(mWebView);

        //웹뷰에서 페이지 이동을 하기위한 설정
        mWebView.setWebViewClient(new WebViewClient());
    }

    public void webviewInit(WebView webView){

        // 캐시 초기화
        webView.clearCache(true);
        webView.clearHistory();

        WebSettings settings = this.mWebView.getSettings();

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

    private long backBtnTime = 0;

    // 뒤로가기 버튼 이벤트
    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        // 웹뷰 히스토리 상 뒤로 갈수 있을 경우
        if(mWebView.canGoBack()){
            mWebView.goBack();
        } else if (0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
        } else {
            // 웹뷰 히스토리 상 뒤로 갈수 없을 경우
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
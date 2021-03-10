package com.osdew.sample_loadappinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private TextView appId;
    private TextView appVer;
    private TextView osVer;
    private TextView modelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //앱 ID ( UUID )
        String appIdValue = UUID.randomUUID().toString();

        //앱 버전
        String appVerValue = "";
        try {
            PackageInfo pi = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            appVerValue = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) { }

        //OS 버전
        String osVerValue = "android "+Build.VERSION.RELEASE;

        //휴대폰 모델명
        String modelNameValue = Build.MODEL;

        appId = findViewById(R.id.appId);
        appVer = findViewById(R.id.appVer);
        osVer = findViewById(R.id.osVer);
        modelName = findViewById(R.id.modelName);

        appId.setText(appIdValue);
        appVer.setText(appVerValue);
        osVer.setText(osVerValue);
        modelName.setText(modelNameValue);
    }
}
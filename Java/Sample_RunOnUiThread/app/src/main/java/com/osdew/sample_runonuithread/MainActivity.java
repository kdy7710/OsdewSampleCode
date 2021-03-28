package com.osdew.sample_runonuithread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String[] mArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        mArr = new String[]{"테스트 배열[0]", "테스트 배열[1]", "테스트 배열[2]", "테스트 배열[3]", "테스트 배열[4]",
                "테스트 배열[5]", "테스트 배열[6]", "테스트 배열[7]", "테스트 배열[8]", "테스트 배열[9]"};

        // 버튼 클릭 이벤트
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // 스레드 생성
                BackgroundThread mThread = new BackgroundThread();

                // 스레드 실행
                mThread.start();
            }
        });
    }

    class BackgroundThread extends Thread{
        @Override
        public void run() {
            Log.d("Thread", "스레드 실행");

            for (int i=0; i<mArr.length; i++){
                Log.d("Thread", "배열 값 : " + mArr[i]);

                int indexNum = i;

                // runOnUiThread 선언
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(mArr[indexNum]);
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (Exception e){}
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText("스레드 종료");
                }
            });
            Log.d("Thread", "스레드 종료");
        }
    }
}
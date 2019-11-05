package com.tones.allthatgoodstuff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private static int loadTime = 3000;
    ProgressBar myProgressBar;
    int myCounter = 0;
    private Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        makeProgress();

        myHandler.postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },loadTime);
    }

    public void makeProgress(){
        myProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        final Timer myTimer = new Timer();
        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {
                myCounter+=4;
                myProgressBar.setProgress(myCounter);

                if(myCounter == 100){
                    myTimer.cancel();
                }
            }
        };

        myTimer.schedule(myTimerTask,0,100);
    }
}
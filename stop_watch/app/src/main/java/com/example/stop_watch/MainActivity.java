package com.example.stop_watch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
private int second;
private boolean running;
private boolean wasRunning;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    public void onStart(View view){
        running=true;
    }

    public void onStop(View view){
        running=false;
    }

    public void onRefresh(View view){
        running=false;
        second=0;
    }

    public void onPause(){
        super.onPause();
        wasRunning =running;
        running=false;

    }

    public void onResume() {
        super.onResume();
        if (wasRunning) {

            running = true;

        }
    }
  /*  public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    } */

    public  void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",second);
        outState.putBoolean("running",running);
        outState.putBoolean("wasRunning",wasRunning);



    }

    private void runTimer() {
        TextView textView = findViewById(R.id.TextView);
        Handler handler =new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours =second/3600;
                int minute =(second% 3600)/60;
                int secs =second % 60;


                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minute,secs);
                textView.setText(time);

                if(running){
                    second++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}
package com.madapplication.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class sleeptracker extends AppCompatActivity {

    private  boolean isRunning = false;
    private long pauseOffset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleeptracker);

        Chronometer chronometer = findViewById(R.id.custom_chronometer);
        Button btnstart = findViewById(R.id.startbutton);
        Button btnpause = findViewById(R.id.pausebutton);
        Button btnreset = findViewById(R.id.resetbutton);

        chronometer.setFormat("Timer: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer chronometer){
                if((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 50_000){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(sleeptracker.this,  "50 sec completed", Toast.LENGTH_SHORT).show();
                }
            }
        } );


        btnstart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!isRunning){
                    chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    chronometer.start();
                    isRunning = true;
                }
            }
        });

        btnpause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(isRunning){
                    chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    isRunning = false;
                }
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
                pauseOffset = 0;
                isRunning = false;
            }
        });


        android.widget.ImageView backarrow = findViewById(R.id.backarrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (sleeptracker.this, HealthTracker.class);
                startActivity(intent);
            }
        });


    }
}
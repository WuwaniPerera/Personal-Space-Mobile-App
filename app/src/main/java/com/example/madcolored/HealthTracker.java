package com.madapplication.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HealthTracker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tracker);


        android.widget.Button bmibutton = findViewById(R.id.bmibutton);

        bmibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HealthTracker.this, MainActivity.class);
                startActivity(intent);
            }
        });

        android.widget.Button reportbutton = findViewById(R.id.reportbutton);

        reportbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HealthTracker.this, sleeptracker.class);
                startActivity(intent);
            }
        });

        android.widget.Button detailsbutton = findViewById(R.id.detailsbutton);

        detailsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HealthTracker.this, detailspage.class);
                startActivity(intent);
            }
        });


    }
}
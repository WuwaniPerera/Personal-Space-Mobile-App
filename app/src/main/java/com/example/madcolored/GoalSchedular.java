package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GoalSchedular extends AppCompatActivity {

    ImageView bckview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_schedular);

        bckview = findViewById(R.id.imageView);

        bckview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GoalSchedular.this, GoalsMain.class);
                startActivity(intent);

            }
        });

    }
}
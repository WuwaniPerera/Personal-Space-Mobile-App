package com.example.madcolored;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    Button btnLogOut;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth = FirebaseAuth.getInstance();

        btnLogOut = findViewById(R.id.btnLogOut);

        ImageView imgMoods = findViewById(R.id.imgMoods);

        imgMoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DashboardActivity.this, EntriesMoods.class);
                startActivity(intent);

            }
        });

        TextView txtMoods = findViewById(R.id.txtMoods);

        txtMoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DashboardActivity.this, EntriesMoods.class);
                startActivity(intent);

            }
        });

        btnLogOut.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(DashboardActivity.this,LoginActivity.class));

        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        if(user == null) {
            startActivity(new Intent(DashboardActivity.this,LoginActivity.class));
        }
    }
}
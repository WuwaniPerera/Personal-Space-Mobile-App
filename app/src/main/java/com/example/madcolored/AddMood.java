package com.example.madcolored;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddMood extends AppCompatActivity {

     ImageView imgEntries, imgHome, imgAdd, imgStats;
     EditText edtEnterMood;
     private TextView txtDate;
    Button btnAddMood;

     DatabaseReference dbRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);

        imgEntries = findViewById(R.id.imgEntries);
        imgHome = findViewById(R.id.imgHome);
        imgAdd = findViewById(R.id.imgAdd);
        imgStats = findViewById(R.id.imgStats);
        txtDate = findViewById(R.id.txtDate);
        edtEnterMood = findViewById(R.id.edtEnterMood);
        btnAddMood = findViewById(R.id.btnAddMood);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Moods");

//Add button
        btnAddMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertMood();
            }
        });

//Calendar view

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddMood.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = dayOfMonth+"-"+month+"-"+year;
                        txtDate.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });

        imgEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddMood.this, RetrieveActivity.class);
                startActivity(intent);

            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddMood.this, DashboardActivity.class);
                startActivity(intent);

            }
        });


        imgStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddMood.this, DashboardActivity.class);
                startActivity(intent);

            }
        });

    }
//Edited
    private void insertMood() {
        String date = txtDate.getText().toString();
        String moodStatus = edtEnterMood.getText().toString();
        String id = dbRef.push().getKey();
        Mood mood = new Mood(id,date,moodStatus);
        assert id!=null;
        dbRef.child(id).setValue(mood);
        Toast.makeText(this, "Mood Inserted!", Toast.LENGTH_SHORT).show();
    }



}
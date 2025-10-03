package com.madapplication.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
//import android.widget.EditText;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView editselectdate,currentDate;
    private EditText currentheight;
    private EditText currentweight, currentage;
//    Button editselectdate;

    TextView eDate,eHeight,eWeight,eAge;

    TextView btn_save;
    DatabaseReference dbRef;


//    int intweight = 55;
//    int intage = 22;
//    int currentprogress;
//    String mintprogreass = "170";
//    String weight2="55";
//    String age2 = "22";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eDate = findViewById(R.id.editselectdate);
        eHeight = findViewById(R.id.currentheight);
        eWeight = findViewById(R.id.currentweight);
        eAge = findViewById(R.id.currentage);
        editselectdate=findViewById(R.id.editselectdate);

         btn_save = findViewById(R.id.savebutton);

        dbRef = FirebaseDatabase.getInstance().getReference().child("BMI");

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertBMIdata();
            }
        });




//        editselectdate = findViewById(R.id.editselectdate);

        final Calendar calendar = Calendar.getInstance();
         int year = calendar.get(Calendar.YEAR);
         int month = calendar.get(Calendar.MONTH);
         int day = calendar.get(Calendar.DAY_OF_MONTH);


        editselectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = day +"/"+month+"/"+year;
                        editselectdate.setText(date);

                    }
                },year , month, day);
                dialog.show();
            }
        });



        android.widget.ImageView backarrow = findViewById(R.id.backarrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, HealthTracker.class);
                startActivity(intent);
            }
        });




        android.widget.Button calculatebmi = findViewById(R.id.calculatebmi);
        calculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                if (mintprogreass.equals("0"))
//                {
//                    Toast.makeText(getApplicationContext(),"Select Your Height First", Toast.LENGTH_SHORT).show();
//                }
//                else if(intage == 0 || intage<0)
//                {
//                    Toast.makeText(getApplicationContext(),"Age Is Incorrect", Toast.LENGTH_SHORT).show();
//                }
//                else if(intweight == 0 || intweight<0)
//                {
//                    Toast.makeText(getApplicationContext(),"Weight Is Incorrect", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
                    Intent intent = new Intent (MainActivity.this, splash.class);
//                    intent.putExtra("height",mintprogreass);
//                    intent.putExtra("weight",weight2);
//                    intent.putExtra("age",age2);
//
                    startActivity(intent);
                }
//
//            }
        });

        android.widget.Button resetdetails = findViewById(R.id.resetdetails);
        resetdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        currentDate=findViewById(R.id.currentDate);


        currentheight = findViewById(R.id.currentheight);
        currentweight = findViewById(R.id.currentweight);
        currentage = findViewById(R.id.currentage);

//        ImageView decrementweight = findViewById(R.id.minus);
//        ImageView incrementweight = findViewById(R.id.incrementweight);
//        ImageView incrementage = findViewById(R.id.incrementage);
//        ImageView decrementage = findViewById(R.id.decrementage);
//        SeekBar seekbarforheight = findViewById(R.id.seekbarforheight);

//        seekbarforheight.setMax(400);
//        seekbarforheight.setProgress(170);
//        seekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                currentprogress = i;
//                mintprogreass = String.valueOf(currentprogress);
//                currentheight.setText(mintprogreass);
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

//        incrementweight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intweight = intweight+1;
//                weight2 = String.valueOf(intweight);
//                currentweight.setText(weight2);
//            }
//        });

//        decrementweight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intweight = intweight-1;
//                weight2 = String.valueOf(intweight);
//                currentweight.setText(weight2);
//            }
//        });

//        incrementage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intage = intage+1;
//                age2 = String.valueOf(intage);
//                currentage.setText(age2);
//            }
//        });

//        decrementage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intage = intage-1;
//                age2 = String.valueOf(intage);
//                currentage.setText(age2);
//            }
//        });

    }

    public void insertBMIdata(){
        String date = eDate.getText().toString();
        String height = eHeight.getText().toString();
        String weight = eWeight.getText().toString();
        String age = eAge.getText().toString();
        String id = dbRef.push().getKey();

        BMI bmiObj = new BMI(date,height,weight,age);
        assert id!=null;
        dbRef.child(id).setValue(bmiObj);
        Toast.makeText(getApplicationContext(),"Data saved successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }




}
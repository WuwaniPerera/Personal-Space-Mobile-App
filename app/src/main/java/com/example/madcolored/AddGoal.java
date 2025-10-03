package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddGoal extends AppCompatActivity {

    private TextView EndDate;
    private TextView StartDate;

    ImageView addBack;

   // to retrive data
    EditText eGoal ;
    TextView eEndDate,eStartDate;

    Button btn_save;

    //database reference variable
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);

        EndDate = findViewById(R.id.eEndDate);
        StartDate = findViewById(R.id.StartDate);

        final Calendar calender = Calendar.getInstance();
        final int year = calender.get(Calendar.YEAR);
        final int month = calender.get(Calendar.MONTH);
        final int day = calender.get(Calendar.DAY_OF_MONTH);


        //calendar popup
        EndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddGoal.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {
                        month = month + 1;
                        String date = dayOfmonth+ "/" + month + "/" + year;
                        EndDate.setText(date);
                    }


                }, year, month, day);
                dialog.show();
            }
        });

        //calendar popup
        StartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddGoal.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {
                        month = month + 1;
                        String date = dayOfmonth + "/" + month + "/" + year;
                        StartDate.setText(date);
                    }


                }, year, month, day);
                dialog.show();
            }
        });

        //variables to pass data into db
        eGoal = findViewById(R.id.txtAddNGoal);
        eStartDate = findViewById(R.id.StartDate);
        eEndDate = findViewById(R.id.eEndDate);

        btn_save = findViewById(R.id.btnSave);


           //db connection
        dbRef = FirebaseDatabase.getInstance().getReference().child("Goals");

//        goalObj = new Goals();

        addBack = findViewById(R.id.imageView);

        addBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddGoal.this, GoalsMain.class);
                startActivity(intent);

            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertGoalData();
            }
        });
    }

    //insert function
        private void insertGoalData(){

            String goal =eGoal.getText().toString();
            String startDate =eStartDate.getText().toString();
            String endDate =eEndDate.getText().toString();
            String id = dbRef.push().getKey();

            //passing data though constructor
              Goals goalObj = new Goals(id,goal,startDate,endDate);
               assert id!=null;
               dbRef.child(id).setValue(goalObj);
     Toast.makeText(getApplicationContext(),"Data Saved Successfully",Toast.LENGTH_SHORT).show();
       ClearControls();
 }

// clear input filed
  public void ClearControls() {
        eGoal.setText("");
        eStartDate.setText("");
        eEndDate.setText("");
        }


    }










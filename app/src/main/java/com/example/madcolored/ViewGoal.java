package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class ViewGoal extends AppCompatActivity {

    ImageView backmain;

    private TextView UpdateEndDate;

//    TextView UpdateTextDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_goal);

        backmain = findViewById(R.id.imageView);
        UpdateEndDate=findViewById(R.id.UpdateTextDate);

        final Calendar calender = Calendar.getInstance();
        final int year = calender.get(Calendar.YEAR);
        final int month = calender.get(Calendar.MONTH);
        final int day = calender.get(Calendar.DAY_OF_MONTH);

        UpdateEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(ViewGoal.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {
                        month = month +1;
                        String date = day+"/"+month+"/"+year;
                        UpdateEndDate.setText(date);
                    }


                },year,month,day);
                dialog.show();
            }
        });

        backmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ViewGoal.this, GoalsMain.class);
                startActivity(intent);



            }
        });




    }
}
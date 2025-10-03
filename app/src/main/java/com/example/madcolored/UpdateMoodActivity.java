package com.example.madcolored;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class UpdateMoodActivity extends AppCompatActivity {

    TextView txtUpdateDate;
    EditText edtUpdateMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mood);

        txtUpdateDate = findViewById(R.id.txtUpdateDate);
        edtUpdateMood = findViewById(R.id.edtUpdateMood);

        //Calendar view

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        txtUpdateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(UpdateMoodActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = dayOfMonth+"-"+month+"-"+year;
                        txtUpdateDate.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });



    }
}
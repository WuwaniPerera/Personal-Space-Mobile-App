package com.example.personalspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class DiaryEntry extends AppCompatActivity {

    EditText etDate, etTitle, etContent;
    Button btnSave;
    DatabaseReference diaryDbRef;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entry);

        etDate = findViewById(R.id.diaryDate);
        etTitle = findViewById(R.id.diaryTitle);

        btnSave = findViewById(R.id.btnSave);

        diaryDbRef = FirebaseDatabase.getInstance().getReference().child("Diary");

        etContent = findViewById(R.id.diaryContent);
        Button btnSpeak = findViewById(R.id.btnSpeaker);

        //Set onClick listener to Speaker
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //using tts library
                tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        if(i==TextToSpeech.SUCCESS){
                            tts.setLanguage(Locale.US);
                            tts.setSpeechRate(1.0f);
                            tts.speak(etContent.getText().toString(),TextToSpeech.QUEUE_ADD,null,null);
                        }
                    }
                });
            }
        });
    }

    //Method to clear the input fields after inserting data
    public void ClearControls(){
        etDate.setText("");
        etTitle.setText("");
        etContent.setText("");
    }


    private void saveData(){
        //validate if input fields are empty
        if (TextUtils.isEmpty(etDate.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter date", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etTitle.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter a title", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etContent.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter diary content", Toast.LENGTH_SHORT).show();

        //If input fields are not empty, insert diary entry to the database
        else{
            String date = etDate.getText().toString();
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();
            String id = diaryDbRef.push().getKey();
            Diary diary = new Diary(id, date, title, content);
            assert id != null;
            diaryDbRef.child(id).setValue(diary);
            Toast.makeText(this, "Diary entry added!", Toast.LENGTH_SHORT).show();
            ClearControls();    //clear input fields
        }

    }


    public void ClickBtnClose(View view)
    {
        Intent i = new Intent(DiaryEntry.this, MainActivityDiary.class);
        startActivity(i);
    }

    public void ClickBtnSave(View view)
    {
        saveData();
        /*Intent i = new Intent(DiaryEntry.this, MainActivityDiary.class);
        startActivity(i);*/
    }

}
package com.example.madcolored;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.text.TextRunShaper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RetrieveActivity extends AppCompatActivity {

    ListView myListView;
    List<Mood> moodList;
    Button btnSeeMore;
    TextView rDate;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve2);

        myListView = findViewById(R.id.myListView);

        btnSeeMore = findViewById(R.id.btnSeeMore);


        moodList = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("Moods");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                moodList.clear();

                for (DataSnapshot moodDatasnap : snapshot.getChildren()) {
                    Mood mood = moodDatasnap.getValue(Mood.class);
                    moodList.add(mood);
                }
                ListAdapter adapter = new ListAdapter(RetrieveActivity.this,moodList);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //set itemLong listener on listview item

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               Mood mood = moodList.get(i);
                showUpdateDialog(mood.getId(),mood.getDate());
                return false;
            }
        });





    }

    public void ShowPopUpMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.show();
    }



    public void ClickEdit(MenuItem item) {
        Intent i = new Intent(RetrieveActivity.this, UpdateMoodActivity.class);
        startActivity(i);
    }

    public void ClickDelete(MenuItem item) {
        //Toast
    }

    ////

    private void showUpdateDialog(String id, String name) {
        final AlertDialog.Builder mDialog = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        LayoutInflater inflater = getLayoutInflater();
        View mDiaLogView = inflater.inflate(R.layout.update_dialog, null);

        mDialog.setView(mDiaLogView);

        //create view references

        final TextView txtUDate = mDiaLogView.findViewById(R.id.txtUDate);
        final EditText edtUMood = mDiaLogView.findViewById(R.id.edtUMood);
        Button btnU = mDiaLogView.findViewById(R.id.btnU);

        mDialog.setTitle("Updating record " + name);
        final AlertDialog alertDialog = mDialog.create();
        alertDialog.show();

        //////
        //Calendar view

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        txtUDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(RetrieveActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = dayOfMonth+"-"+month+"-"+year;
                        txtUDate.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });

        ////////

        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update data in DB
                //get values from view
                String newDate = txtUDate.getText().toString();
                String newMood = edtUMood.getText().toString();

                updateMoodData(id, newDate, newMood);

                Toast.makeText(RetrieveActivity.this, "Record Updated!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    private void updateMoodData(String id, String date, String moodU) {
        //creating DB reference
        DatabaseReference dbRef =  FirebaseDatabase.getInstance().getReference("Moods").child(id);
        Mood mood = new Mood(id, date, moodU);
        dbRef.setValue(mood);
    }

}
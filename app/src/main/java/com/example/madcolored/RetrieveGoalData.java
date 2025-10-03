package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RetrieveGoalData extends AppCompatActivity {

    ListView myListView;
    List <Goals> goalsList;



    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_data);

        myListView = findViewById(R.id.newListView);
        goalsList = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("Goals");


             //data retrieve function
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                goalsList.clear();

                for (DataSnapshot goalDataSnapshot : dataSnapshot.getChildren()) {

                    Goals goals = goalDataSnapshot.getValue(Goals.class);
                    goalsList.add(goals);
                }

                GoalListAdapter adapter = new GoalListAdapter(RetrieveGoalData.this, goalsList);
                myListView.setAdapter(adapter);

                //set itemLong listenr  on listview item

                myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                       Goals goals = goalsList.get(position);
                       showUpdateDialog(goals.getId(),goals.getGoal());

                        return false;
                    }
                });

            }


                @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    //update data function
    private void showUpdateDialog(String id ,String name){

        final AlertDialog.Builder mDialog= new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_dialog,null);

        mDialog.setView(mDialogView);

        //create views references
        EditText edUpdateGoal = mDialogView.findViewById(R.id.edUpdateGoal);
        TextView edUpdateEndDate = mDialogView.findViewById(R.id.edUpdateEndDate);
        Button btnUpdate = mDialogView.findViewById(R.id.ebtnUpdate);
        Button btnDelete =mDialogView.findViewById(R.id.ebtnDelete);

        mDialog.setTitle("Updating "+ name + " goal");
        final AlertDialog alertDialog = mDialog.create();
        alertDialog.show();

        final Calendar calender = Calendar.getInstance();
        final int year = calender.get(Calendar.YEAR);
        final int month = calender.get(Calendar.MONTH);
        final int day = calender.get(Calendar.DAY_OF_MONTH);



        edUpdateEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(RetrieveGoalData.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {
                        month = month + 1;
                        String date = dayOfmonth + "/" + month + "/" + year;
                        edUpdateEndDate.setText(date);
                    }


                }, year, month, day);
                dialog.show();
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here update data in database
                //get values from view
                String newGoal =edUpdateGoal.getText().toString();
                String newEndDate = edUpdateEndDate.getText().toString();

                updateData(id,newGoal,newEndDate);

                Toast.makeText(RetrieveGoalData.this,"Record updated",Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord(id);
            }
        });


    }

    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    //delete function
    private void deleteRecord(String id){
        //Create Database reference
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Goals").child(id);
        //delete only one record in database
        Task<Void> mTask = dbRef.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                 showToast("Deleted");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                   showToast("Error Deleting Record");
            }
        });
    }


    //update data function
    private void updateData(String id,String goal, String newEndDate){

        DatabaseReference dbRef =FirebaseDatabase.getInstance().getReference("Goals").child(id);
        Goals goals = new Goals(id, goal, newEndDate);
        dbRef.setValue(goals);
    }


}
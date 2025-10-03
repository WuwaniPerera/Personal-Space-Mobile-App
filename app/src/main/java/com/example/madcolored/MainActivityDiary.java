package com.example.personalspace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
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
import java.util.List;

public class MainActivityDiary extends AppCompatActivity {
    ListView myListView;
    List<Diary> diaryList;

    DatabaseReference diaryDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = findViewById(R.id.myListView);
        diaryList = new ArrayList<>();

        diaryDbRef = FirebaseDatabase.getInstance().getReference("Diary");

        diaryDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                diaryList.clear();

                for (DataSnapshot diaryDatasnap : snapshot.getChildren()){
                    Diary diary = diaryDatasnap.getValue(Diary.class);
                    diaryList.add(diary);
                }
                ListAdapterDiary adapter = new ListAdapterDiary(MainActivityDiary.this, diaryList);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Diary diary = diaryList.get(i);
                showUpdateDialog(diary.getId(), diary.getDate());

                return false;
            }
        });
    }

    private void showUpdateDialog(String id, String name){
        AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_dialog,null);

        mDialog.setView(mDialogView);

        EditText etUpdateDate = mDialogView.findViewById( R.id.etUpdateDate);
        EditText etUpdateTitle = mDialogView.findViewById(R.id.etUpdateTitle);
        EditText etUpdateContent = mDialogView.findViewById(R.id.etUpdateContent);
        Button btnUpdate = mDialogView.findViewById(R.id.btnUpdate);
        Button btnDelete = mDialogView.findViewById(R.id.btnDelete);

        mDialog.setTitle("Updating Record " + name);
        final AlertDialog alertDialog = mDialog.create();
        mDialog.show();

        //set onClick listener on update button
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Update data in database and get values from view
                String newDate = etUpdateDate.getText().toString();
                String newTitle = etUpdateTitle.getText().toString();
                String newContent = etUpdateContent.getText().toString();

                updateData(id, newDate, newTitle, newContent);

                //Display record updated using toast message
                Toast.makeText(MainActivityDiary.this, "Record Updated", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        //set onClick listener on delete button
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecord(id);
            }
        });
    }

    private void updateData(String id, String date, String title, String content){
        //creating database reference
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("Diary").child(id);
        Diary diary = new Diary(id, date, title, content);
        DbRef.setValue(diary);
    }

    //Method to display a message using Toast
    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //Delete method implementation
    private void deleteRecord(String id){
        //creating database reference
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("Diary").child(id);
        Task<Void> mTask = DbRef.removeValue();

        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                showToast("Deleted!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showToast("Error deleting record!");
            }
        });
    }


    public void showPopupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.menu_popup, popupMenu.getMenu());
        popupMenu.show();
    }

    public void ClickBtnNew(View view)
    {
        Intent i = new Intent(MainActivityDiary.this,DiaryEntry.class);
        startActivity(i);
    }

    public void ClickEdit(MenuItem item) {
        Intent i = new Intent(MainActivityDiary.this, DiaryEntry.class);
        startActivity(i);
    }

    public void ClickDelete(MenuItem item) {
        Toast.makeText(this, "Data deleted!", Toast.LENGTH_SHORT).show();
        //Deleted Toast Message
    }


}
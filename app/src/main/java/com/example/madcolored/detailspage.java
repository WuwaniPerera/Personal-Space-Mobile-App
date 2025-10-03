package com.madapplication.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class detailspage extends AppCompatActivity {

    ListView myListview;
    List<BMI> bmiList;

    DatabaseReference dbRef;

//    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailspage);
//        b1 = findViewById(R.id.b1);
//        registerForContextMenu(b1);

        myListview = findViewById(R.id.myListView);
        bmiList = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("BMI");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bmiList.clear();

                for (DataSnapshot bmiDataSnap : snapshot.getChildren()) {
                    BMI bmi = bmiDataSnap.getValue(BMI.class);
                    bmiList.add(bmi);
                }
                ListAdapter adapter = new ListAdapter(detailspage.this, bmiList);
                myListview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        android.widget.Button backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (detailspage.this, HealthTracker.class);
                startActivity(intent);
            }
        });


        myListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
             @Override
             public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                 BMI bmi = bmiList.get(i);
                 showUpdateDialog(bmi.getId(),bmi.getDate());
                 System.out.println(bmi.getId()+";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                 return false;
             }
         });
    }


        private void showUpdateDialog(String id, String name ){
            final AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View mDialogView = inflater.inflate(R.layout.update_dialog,null);

            mDialog.setView(mDialogView);

            final EditText Updateage = mDialogView.findViewById(R.id.Updateage);
            final EditText UpdateDate = mDialogView.findViewById(R.id.UpdateDate);
            final EditText updateHeight = mDialogView.findViewById(R.id.updateHeight);
            final EditText updateWeight = mDialogView.findViewById(R.id.updateWeight);

            Button updatebutton = mDialogView.findViewById(R.id.updatebutton);
            Button deletebutton = mDialogView.findViewById(R.id.deletebutton);

            mDialog.setTitle("Updating" + name+"record");
            final AlertDialog alertDialog = mDialog.create();
            alertDialog.show();

            updatebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String date=UpdateDate.getText().toString();
                    String age = Updateage.getText().toString();
                    String height = updateHeight.getText().toString();
                    String weight = updateWeight.getText().toString();

                    updateData(id,date,age,height,weight);

                    Toast.makeText(detailspage.this, "Record Updated", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            });

            deletebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteRecord(id);
                    alertDialog.dismiss();
                }
            });


        }

        private void showToast(String message){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

        private void deleteRecord(String id){
                 DatabaseReference Dbref = FirebaseDatabase.getInstance().getReference("BMI").child(id);


            Task<Void> mTask = Dbref.removeValue();
            mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    showToast("Deleted successfully");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    showToast("Error deleting record");
                }
            });
        }







    private void updateData(String id,String date, String height, String weight,String age){
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("BMI").child(id);
        BMI bmi = new BMI(id,height,weight,age);
        DbRef.setValue(bmi);


    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater menuInflater = new MenuInflater(this);
//        menuInflater.inflate(R.menu.menu_item, menu);
//    }

//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.item1:
//                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
//            case R.id.item2:
//                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
//        }
//        return super.onContextItemSelected(item);
//    }

//    public void ShowPopupItems(View view){
//        PopupMenu popupMenu = new PopupMenu(this,view);
//        MenuInflater inflater=popupMenu.getMenuInflater();
//        inflater.inflate(R.menu.menu_item,popupMenu.getMenu());
//        popupMenu.show();
//    }

//    public void ClickEdit(MenuItem item){
//        Intent i = new Intent(detailspage.this, Editpage.class);
//        startActivity(i);
//    }

//    public void ClickDelete(MenuItem item){
//        //Toast
//    }


}
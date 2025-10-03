package com.example.madcolored;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class EntriesMoods extends AppCompatActivity {

    ImageView imgEntries, imgHome, imgAdd, imgStats, imgMore1;
    Button btnSeeMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries_moods);

        imgEntries = findViewById(R.id.imgEntries);
        imgHome = findViewById(R.id.imgHome);
        imgAdd = findViewById(R.id.imgAdd);
        imgStats = findViewById(R.id.imgStats);
        btnSeeMore = findViewById(R.id.btnSeeMore);

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EntriesMoods.this, AddMood.class);
                startActivity(intent);

            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EntriesMoods.this, DashboardActivity.class);
                startActivity(intent);

            }
        });


        imgStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EntriesMoods.this, DashboardActivity.class);
                startActivity(intent);

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
        Intent i = new Intent(EntriesMoods.this, UpdateMoodActivity.class);
        startActivity(i);
    }

    public void ClickDelete(MenuItem item) {
        //Toast
    }
}
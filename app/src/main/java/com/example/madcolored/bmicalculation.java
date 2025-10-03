package com.madapplication.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class bmicalculation extends AppCompatActivity {

//    TextView bmidisplay, bmicategory, value;
//    ImageView imageview;
//    Intent intent;
//    int mbmi;
//    int intbmi;

//    String height;
//    String weight;
//    int intheight, intweight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculation);


//        intent = getIntent();

//        bmidisplay = findViewById(R.id.bmidisplay);
//        bmicategory = findViewById(R.id.bmidisplay);
//        value = findViewById(R.id.value);
//        imageview = findViewById(R.id.imageview);

//        height = intent.getStringExtra("height");
//        weight = intent.getStringExtra("weight");
//
//        intheight = Integer.parseInt(String.valueOf(Float.parseFloat(height)));
//        intweight = Integer.parseInt(String.valueOf(Float.parseFloat(weight)));
//
//        intheight = intheight/100;
//
//        intbmi = intweight/(intheight*intheight);
//
//        mbmi = Integer.parseInt(Float.toString(intbmi));

//        if(intbmi<18)
//        {
//            bmidisplay.setText("Below 18.5");
//            bmicategory.setText("Under Weight");
//            imageview.setImageResource(R.drawable.cross1);
//        }
//         else if(intbmi<=18 && intbmi<25)
//        {
//            bmidisplay.setText("18.5-24.9");
//            bmicategory.setText("Normal Weight");
//            imageview.setImageResource(R.drawable.right);
//        }
//         else if(intbmi<=25 && intbmi<30)
//        {
//            bmidisplay.setText("25.0-29.9");
//            bmicategory.setText("Over Weight");
//            imageview.setImageResource(R.drawable.warning2);
//        }
//         else
//        {
//            bmidisplay.setText("30.0-34.9");
//            bmicategory.setText("Obesity");
//            imageview.setImageResource(R.drawable.warn2);
//        }


//        value.setText(mbmi);

        android.widget.Button recheck = findViewById(R.id.recheck);
        recheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (bmicalculation.this, MainActivity.class);
                startActivity(intent);
            }
        });

        android.widget.ImageView backarrow = findViewById(R.id.backarrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (bmicalculation.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
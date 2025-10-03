package com.madapplication.bmicalculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<BMI> bmiList;

    @SuppressLint("ResourceType")
    public ListAdapter(Activity mContext, List<BMI> bmiList){
        super(mContext,R.layout.list_item,bmiList);
        this.mContext = mContext;
        this.bmiList = bmiList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item,null,true);


        TextView tvAge = listItemView.findViewById(R.id.tvAge);
        TextView tvDate = listItemView.findViewById(R.id.tvDate);
        TextView tvHeight = listItemView.findViewById(R.id.tvHeight);
        TextView tvWeight = listItemView.findViewById(R.id.tvWeight);

        BMI bmi = bmiList.get(position);

        tvAge.setText(bmi.getAge());
        tvDate.setText(bmi.getDate());
        tvHeight.setText(bmi.getHeight());
        tvWeight.setText(bmi.getWeight());

        return  listItemView;



    }
}

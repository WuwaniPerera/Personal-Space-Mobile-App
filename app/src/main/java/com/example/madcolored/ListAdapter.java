package com.example.madcolored;

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
    List<Mood> moodList;

    public ListAdapter(Activity mContext, List<Mood> moodList) {
        super(mContext,R.layout.retrieve_list,moodList);
        this.mContext = mContext;
        this.moodList = moodList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.retrieve_list,null,true);

        TextView rDate = listItemView.findViewById(R.id.rDate);
        TextView rMood = listItemView.findViewById(R.id.rMood);

        Mood mood = moodList.get(position);

        rDate.setText(mood.getDate());
        rMood.setText(mood.getMood());

        return listItemView;

    }
}

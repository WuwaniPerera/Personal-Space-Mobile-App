package com.example.personalspace;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapterDiary extends ArrayAdapter {

    private Activity mContext;
    List<Diary> diaryList;

    public ListAdapterDiary(Activity mContext, List<Diary> diaryList){
        super(mContext, R.layout.list_item, diaryList);
        this.mContext = mContext;
        this.diaryList = diaryList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item, null, true);

        TextView tvDate = listItemView.findViewById(R.id.tvDate);
        TextView tvTitle = listItemView.findViewById(R.id.tvTitle);
        TextView tvContent = listItemView.findViewById(R.id.tvContent);

        //Access diaryList list and assign it to diary object
        Diary diary = diaryList.get(position);

        //Retrieve diary entries from the database using getters
        tvDate.setText(diary.getDate());
        tvTitle.setText(diary.getTitle());
        tvContent.setText(diary.getContent());

        return listItemView;
    }

}

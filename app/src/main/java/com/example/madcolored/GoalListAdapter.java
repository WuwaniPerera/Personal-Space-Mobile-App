package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GoalListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<Goals> goalsList;


    public GoalListAdapter(Activity mContext, List<Goals> goalsList) {
        super(mContext, R.layout.list_item, goalsList);
        this.mContext = mContext;
        this.goalsList = goalsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item, null, true);

        TextView ViewGoal = listItemView.findViewById(R.id.tViewGoal);
        TextView ViewEndDate= listItemView.findViewById(R.id.tViewEndDate);

        Goals goals = goalsList.get(position);

        ViewGoal.setText(goals.getGoal());
        ViewEndDate.setText(goals.getEndDate());


        return listItemView;
    }
}

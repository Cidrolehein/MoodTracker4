package com.gacon.julien.moodtracker4.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gacon.julien.moodtracker4.R;
import com.gacon.julien.moodtracker4.models.Json.MoodAndCommentItem;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.AdapterViewHolder> {

    private ArrayList<MoodAndCommentItem> mMoodList;

    public static class AdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView mDate;
        public TextView mMood;
        public TextView mComment;



        public AdapterViewHolder(View itemView) {
            super(itemView);

            mDate = itemView.findViewById(R.id.textViewDate);
            mMood = itemView.findViewById(R.id.textView_Mood);
            mComment = itemView.findViewById(R.id.textViewComment);

        }
    }

    public HistoryAdapter(ArrayList<MoodAndCommentItem> moodList) {
        mMoodList = moodList;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        AdapterViewHolder evh = new AdapterViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        MoodAndCommentItem currentMood = mMoodList.get(position);
/*
        holder.mDate.setText(currentMood.getDate());
        holder.mMood.setText(currentMood.getMood());
        holder.mComment.setText(currentMood.getComment());
*/
    }

    @Override
    public int getItemCount() {
        return mMoodList.size();
    }


}
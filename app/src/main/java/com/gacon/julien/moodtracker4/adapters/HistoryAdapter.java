package com.gacon.julien.moodtracker4.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.gacon.julien.moodtracker4.R;
import com.gacon.julien.moodtracker4.models.Json.HistoryItem;
import java.util.ArrayList;

/**
 * History RecyclerView Adapter >> "Coding in Flow" : https://www.youtube.com/watch?v=17NbUcEts9c
 */

// HistoryAdapter class

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    // HistoryAdapter variables
    private ArrayList<HistoryItem> mHistoryList; // ArrayList for HistoryAdapter method

    // HistoryViewHolder class

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {

        // HistoryViewHolder variables
        public ImageView mImageView; // mood image
        public TextView mTextView1; // date
        public TextView mTextView2; // comment

        // HistoryViewHolder constructor
        public HistoryViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
        } // end of HistoryViewHolder constructor
    } // end of HistoryViewHolder class

    // HistoryAdapter method for data of Arraylist
    public HistoryAdapter(ArrayList<HistoryItem> historyList){
       mHistoryList = historyList;
    }// end of HistoryAdapter method


    /**
     * Methods of ViewHolder
     */

    // OnCreateViewHolder method >> history_items class context
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_items, parent, false); // create the View
        HistoryViewHolder hvh = new HistoryViewHolder(v); // ViewHolder of View v
        return hvh; // return HistoryViewHolder
    } //end of OnCreateViewHolder method


    // onBindViewHolder method for list of items and position
    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryItem currentItem = mHistoryList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource()); // mood image
        holder.mTextView1.setText(currentItem.getText1()); // date
        holder.mTextView2.setText(currentItem.getText2()); // comment
    } //end of onBindViewHolder


    // getItemCount method for the number of items to return
    @Override
    public int getItemCount() {
        return mHistoryList.size(); // = nbr of items in ArrayList
    } // end of getItemCount method

} // end of HistoryAdapter class

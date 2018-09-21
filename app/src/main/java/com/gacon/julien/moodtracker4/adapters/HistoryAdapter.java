package com.gacon.julien.moodtracker4.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        public TextView mTextView1; // date
        public RelativeLayout mRelativeLayout; // relative layout
        public ImageButton mCommentButton; // comment button

        // HistoryViewHolder constructor
        public HistoryViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mRelativeLayout = itemView.findViewById(R.id.relative_layout);
            mCommentButton = itemView.findViewById(R.id.activity_history_comment_btn);

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
    public void onBindViewHolder(@NonNull final HistoryViewHolder holder, int position) {
        final HistoryItem currentItem = mHistoryList.get(position);

        holder.mTextView1.setText(currentItem.getText1()); // date
        holder.mRelativeLayout.setBackgroundColor(currentItem.getImageColor()); // background color

        //Set Comment button if comment exists
        if(!currentItem.getText2().equals("No comment")){
            //Show button + if click on button, show Comment (Toast)
            holder.mCommentButton.setVisibility(ImageButton.VISIBLE);
            holder.mCommentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), currentItem.getText2(), Toast.LENGTH_SHORT).show();
                }}   );
        }

        else{
            holder.mCommentButton.setVisibility(ImageButton.INVISIBLE);
        }

        // Gets the layout params that will allow you to resize the layout
        ViewGroup.LayoutParams params = holder.mRelativeLayout.getLayoutParams();
        params.height = currentItem.getHeight();
        params.width = currentItem.getWidth();
    } //end of onBindViewHolder


    // getItemCount method for the number of items to return
    @Override
    public int getItemCount() {

        //return mHistoryList.size(); // = nbr of items in ArrayList
        int count = 7;
        int i=mHistoryList.size();

            if (mHistoryList != null) {
                if (count>=i) {
                    return mHistoryList.size();
                }
                return count;
            }
            return 0;

    } // end of getItemCount method

} // end of HistoryAdapter class

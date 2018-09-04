package com.gacon.julien.moodtracker4.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.gacon.julien.moodtracker4.R;
import com.gacon.julien.moodtracker4.adapters.HistoryAdapter;
import com.gacon.julien.moodtracker4.models.Json.HistoryItem;
import java.util.ArrayList;

/**
 * History Activity of MoodTracker
 */


//HistoryActivity class

public class HistoryActivity extends AppCompatActivity {

    // HistoryActivities Variables

    private ArrayList<HistoryItem> historyList;

    private RecyclerView mRecyclerView; // RecyclerView create on history_layout
    private RecyclerView.Adapter mAdapter; // bridge our data between ArrayList and RecyclerView
    private RecyclerView.LayoutManager mLayoutManager; // manage the ListView

// onCreate Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        if (historyList == null) {
            historyList = new ArrayList<>();
        }

        // ArrayList of history items
        historyList.add(new HistoryItem(R.drawable.smiley_happy, "Line 1", "Line 2"));
        historyList.add(new HistoryItem(R.drawable.smiley_super_happy, "Line 3", "Line 4"));
        historyList.add(new HistoryItem(R.drawable.smiley_normal, "Line 5", "Line 6"));

        buildRecyclerView(); // create Recycler View

    } // end of onCreate method

    // buildRecyclerView method
    public void buildRecyclerView() {
        // Elements of History Activity
        mRecyclerView = findViewById(R.id.recyclerView); // recyclerView of activity_history layout
        mRecyclerView.setHasFixedSize(true); // fix size of list to increase performance
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new HistoryAdapter(historyList);

        mRecyclerView.setLayoutManager(mLayoutManager); // pass LayoutManager to RecyclerView
        mRecyclerView.setAdapter(mAdapter); // pass Adapter to RecyclerView
    } // end of buildRecyclerView method


} // end of HistoryActivity class

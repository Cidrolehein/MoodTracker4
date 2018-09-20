package com.gacon.julien.moodtracker4.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.gacon.julien.moodtracker4.R;
import com.gacon.julien.moodtracker4.adapters.HistoryAdapter;
import com.gacon.julien.moodtracker4.models.SharedPreferences.MySharedPreferences;

import java.util.ArrayList;

/********************************************************************************
 * MoodTracker by Julien Gacon for OpenClassRooms - 2018
 * History Activity
 ********************************************************************************/

//HistoryActivity class
public class HistoryActivity extends AppCompatActivity {

    /********************************************************************************
     * History Activity variables
     ********************************************************************************/

    private RecyclerView mRecyclerView; // RecyclerView create on history_layout
    private RecyclerView.Adapter mAdapter; // bridge our data between ArrayList and RecyclerView
    private RecyclerView.LayoutManager mLayoutManager; // manage the ListView
    private MySharedPreferences sharedPreferences; // load MySharedPreferences for data
    private Button mPieChartBtn; // btn pie chart

    /********************************************************************************
     * History Activity Life Cycle
     ********************************************************************************/

    // On create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        sharedPreferences = new MySharedPreferences(this); // initialize MySharedPreferences
        sharedPreferences.loadData(); // load data from shared pref

        buildRecyclerView(); // create Recycler View

        // Open Pie Graph Activity
        mPieChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(HistoryActivity.this, GraphActivity.class);
                startActivity(historyActivityIntent);

            } //end of onClick method
        });

    } // end of onCreate method

    /********************************************************************************
     * History Activity Recycler View
     ********************************************************************************/

    // buildRecyclerView method
    public void buildRecyclerView() {
        // Elements of History Activity
        mRecyclerView = findViewById(R.id.recyclerView); // recyclerView of activity_history layout
        mRecyclerView.setHasFixedSize(true); // fix size of list to increase performance
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new HistoryAdapter(sharedPreferences.getHistoryList()); // put data from SharedPreferences to HistoryAdapter
        mPieChartBtn = findViewById(R.id.activity_history_chart_btn); // pie chart btn

        mRecyclerView.setLayoutManager(mLayoutManager); // pass LayoutManager to RecyclerView
        mRecyclerView.setAdapter(mAdapter); // pass Adapter to RecyclerView
    } // end of buildRecyclerView method

} // end of HistoryActivity class

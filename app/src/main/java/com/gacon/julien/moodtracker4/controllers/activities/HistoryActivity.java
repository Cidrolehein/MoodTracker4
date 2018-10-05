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
import com.gacon.julien.moodtracker4.models.Json.HistoryItem;
import com.gacon.julien.moodtracker4.models.SharedPreferences.MapSharedPref;
import com.gacon.julien.moodtracker4.models.SharedPreferences.MySharedPreferences;
import com.gacon.julien.moodtracker4.models.Time.CurrentDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    private MapSharedPref mapSharedPref;
    private Button mPieChartBtn; // btn pie chart
    private ArrayList<HistoryItem> arrayList; // history list
    private Map<String, List<HistoryItem>> groupedHashMap;
    ArrayList<Map<String, List<HistoryItem>>> treeMapArrayList;
    CurrentDate cdate;

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
        mapSharedPref = new MapSharedPref(this);

        // ! condition
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        } // end of condition

        arrayList = sharedPreferences.getHistoryList(); // get history list

        cdate = new CurrentDate();

        groupedHashMap = groupDataIntoTreeMap(arrayList);

        treeMapArrayList = new ArrayList<>();
        for (int i = 0; i < groupedHashMap.size(); i ++) {
            treeMapArrayList.add(i, groupedHashMap);
        }


        ArrayList<String> keylist = new ArrayList<>();
        ArrayList<ArrayList<HistoryItem>> listValues = new ArrayList<>();
        for (Map.Entry<String, List<HistoryItem>> entry : groupedHashMap.entrySet()) {
            String key = entry.getKey();
            keylist.add(key);
            ArrayList<HistoryItem> value = (ArrayList<HistoryItem>) entry.getValue();
            listValues.add(value);
            }

/*
        for (String date : groupedHashMap.keySet()) {
            DateItem dateItem = new DateItem();
            dateItem.setDate(date);
            consolidatedList.add(dateItem);

            for (HistoryItem historyItem : groupedHashMap.get(date)) {
                GeneralItem generalItem = new GeneralItem();
                generalItem.sethistoryItemArray(historyItem);
                consolidatedList.add(generalItem);
            }
        }
*/

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
        mAdapter = new HistoryAdapter(groupedHashMap, treeMapArrayList); // put data from SharedPreferences to HistoryAdapter
        mAdapter.notifyDataSetChanged();
        mPieChartBtn = findViewById(R.id.activity_history_chart_btn); // pie chart btn
        mRecyclerView.setLayoutManager(mLayoutManager); // pass LayoutManager to RecyclerView
        mRecyclerView.setAdapter(mAdapter); // pass Adapter to RecyclerView
    } // end of buildRecyclerView method

    private Map<String, List<HistoryItem>> groupDataIntoTreeMap(ArrayList<HistoryItem> listOfHistoryItems) {

        ArrayList<HistoryItem> consolidatedList;

        consolidatedList = new ArrayList<>();

        if (groupedHashMap == null) {
            groupedHashMap = new TreeMap<String, List<HistoryItem>>();
        }

        for (int i = 0; i < listOfHistoryItems.size(); i++) {
            String hashMapKey = listOfHistoryItems.get(i).getText1();
            for (HistoryItem historyItems : listOfHistoryItems) {

                if (groupedHashMap.containsKey(hashMapKey)) {
                    // The key is already in the HashMap; add the pojo object
                    // against the existing key.
                    mapSharedPref.getHashList();

                } else {
                    // The key is not there in the HashMap; create a new key-value pair
                    ArrayList<HistoryItem> list = new ArrayList<>();
                    list.add(historyItems);
                    consolidatedList.add(historyItems);
                    groupedHashMap.put(hashMapKey, list);
                    //mapSharedPref.getHashMap().put(hashMapKey, list);
                }
            }
        }

        return groupedHashMap;
    }

} // end of HistoryActivity class

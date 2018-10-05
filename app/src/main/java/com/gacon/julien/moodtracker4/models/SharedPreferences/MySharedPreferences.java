package com.gacon.julien.moodtracker4.models.SharedPreferences;

import android.content.Context;
import com.gacon.julien.moodtracker4.models.Json.HistoryItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * class to manage SharedPreferences
 */

// Shared Preferences class
public class MySharedPreferences {

    // MySharedPreferences variables
    public static final String SHARE_PREFERENCES = "SHARE_PREFERENCES"; // key of SharedPreferences context
    public static final String MOOD_LIST = "MOOD_LIST"; // key of shared preferences mood list
    private static Context context; // get context
    private ArrayList<HistoryItem> historyList; // history array list

    // constructor
    public MySharedPreferences (Context context) {
        this.context = context;
    } // end of constructor

    // save data method
    public void saveData() {

        // initialize SharedPreferences
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCES, 0);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson(); // initialize Gson for Json

        //To Json
        String json = gson.toJson(historyList);

        editor.putString(MOOD_LIST, json); // put string into json list
        editor.commit();

    } // end of save data method

    // load data method
    public void loadData() {

        Gson gson = new Gson(); // initialize Gson for Json

        // initialize SharedPreferences
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCES, 0);
        String json = sharedPreferences.getString(MOOD_LIST, null);

        Type type = new TypeToken<ArrayList<HistoryItem>>() {
        }.getType(); // add data to the list of Json

        historyList = gson.fromJson(json, type); // load data

        if (historyList == null) {
            historyList = new ArrayList<>();
        }

    } // end of load data method

    /**
     * setters and getters
     */

    // getHistoryList
    public ArrayList<HistoryItem> getHistoryList() {
        return historyList;
    } // end of getHistoryList

    // setHistoryList
    public void setHistoryList(ArrayList<HistoryItem> historyList) {
        this.historyList = historyList;
    } //end of setHistoryList

} // end of MySharedPreferences class

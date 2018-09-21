package com.gacon.julien.moodtracker4.models.Time;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TimeSharedPreferences {

    // MySharedPreferences variables
    public static final String TIME_SHARE_PREFERENCES = "TIME_SHARE_PREFERENCES"; // key of SharedPreferences context
    public static final String TIME_LIST = "TIME_LIST"; // key of shared preferences mood list
    private static Context context; // get context
    private ArrayList<Time> timelist; // history array list

    // constructor
    public TimeSharedPreferences (Context context) {
        this.context = context;
    } // end of constructor

    // save data method
    public void saveData() {

        // initialize SharedPreferences
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(TIME_SHARE_PREFERENCES, 0);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson(); // initialize Gson for Json

        //To Json
        String json = gson.toJson(timelist);

        editor.putString(TIME_LIST, json); // put string into json list
        editor.commit();

    } // end of save data method

    // load data method
    public void loadData() {

        Gson gson = new Gson(); // initialize Gson for Json

        // initialize SharedPreferences
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(TIME_SHARE_PREFERENCES, 0);
        String json = sharedPreferences.getString(TIME_LIST, null);

        Type type = new TypeToken<ArrayList<Time>>() {
        }.getType(); // add data to the list of Json

        timelist = gson.fromJson(json, type); // load data

    } // end of load data method

    /**
     * setters and getters
     */

    // getHistoryList
    public ArrayList<Time> getTimeList() {
        return timelist;
    } // end of getHistoryList

    // setHistoryList
    public void setTimeList(ArrayList<Time> timeList) {
        this.timelist = timeList;
    } //end of setHistoryList
}

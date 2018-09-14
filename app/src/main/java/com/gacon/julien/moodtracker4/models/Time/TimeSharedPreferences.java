package com.gacon.julien.moodtracker4.models.Time;

import android.content.Context;

public class TimeSharedPreferences {

    // TimeSharedPreferences variables
    public static final String SHARE_PREFERENCES = "SHARE_PREFERENCES"; // key of SharedPreferences context
    public static final String TIME = "TIME"; // key of shared preferences mood list
    private static Context context; // get context
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mBetweenDay;

    // constructor
    public TimeSharedPreferences(Context context) {
        this.context = context;

    } // end of constructor

    // save data method
    public void saveData() {

        // initialize SharedPreferences
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCES, 0);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("TodayYear", mYear); // put year
        editor.putInt("CurrentMonth", mMonth); // put month
        editor.putInt("CurrentDay", mDay); // put day
        editor.putInt("BetweenDay", mBetweenDay); // mBetweenDay
        editor.commit();

    } // end of save data method

    // load data method
    public void loadData() {

        // initialize SharedPreferences
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCES, 0);
        mYear = sharedPreferences.getInt("TodayYear", 0);
        mMonth = sharedPreferences.getInt("CurrentMonth", 0);
        mDay = sharedPreferences.getInt("CurrentDay", 0);
        mBetweenDay = sharedPreferences.getInt("BetweenDay", 0);

    } // end of loadData method

    public void setYear(int year) {
        mYear = year;
    }

    public void setMonth(int month) {
        mMonth = month;
    }

    public void setDay(int day) {
        mDay = day;
    }

    public int getBetweenDay() {
        return mBetweenDay;
    }

    public int getYear() {
        return mYear;
    }

    public int getMonth() {
        return mMonth;
    }

    public int getDay() {
        return mDay;
    }
}

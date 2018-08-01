package com.gacon.julien.moodtracker4.models.SharedPreferences;

import android.content.Context;

import com.gacon.julien.moodtracker4.models.Json.MoodAndCommentItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.gacon.julien.moodtracker4.controllers.activities.MainActivity.MOOD_LIST;

public class MySharedPreferences {

    public static final String SHARE_PREFERENCES = "SHARE_PREFERENCES";

    private static Context context;

    private ArrayList<MoodAndCommentItem> moodAndCommentItemList;

    public MySharedPreferences (Context context) {
        this.context = context;
    }

    public void saveData() {

        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCES, 0);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        //To Json
        String json = gson.toJson(moodAndCommentItemList);

        editor.putString(MOOD_LIST, json);
        editor.commit();

    }

    public void loadData() {

        Gson gson = new Gson();
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCES, 0);
        String json = sharedPreferences.getString(MOOD_LIST, null);
        Type type = new TypeToken<ArrayList<MoodAndCommentItem>>() {
        }.getType();
        moodAndCommentItemList = gson.fromJson(json, type);
        int i = 1;
    }

    public void setMoodAndCommentItemList(ArrayList<MoodAndCommentItem> moodAndCommentItemList) {
        this.moodAndCommentItemList = moodAndCommentItemList;
    }

    public ArrayList<MoodAndCommentItem> getMoodAndCommentItemList() {
        return moodAndCommentItemList;
    }

}

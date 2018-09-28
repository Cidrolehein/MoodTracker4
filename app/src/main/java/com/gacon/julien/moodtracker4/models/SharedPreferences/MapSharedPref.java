package com.gacon.julien.moodtracker4.models.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.gacon.julien.moodtracker4.models.Json.HistoryItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MapSharedPref {

    public static final String MAP_SHARE_PREFERENCES = "MAP_SHARE_PREFERENCES"; // key of SharedPreferences context
    public static final String MAP = "MAP"; // key of shared preferences mood list
    private static Context context; // get context

    public MapSharedPref(Context context) {
        this.context = context;
    }

    /**
     *     Save and get HashMap in SharedPreference
     */

    public void saveHashMap(String key , Object obj) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(MAP_SHARE_PREFERENCES, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(key,json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public HashMap<String, ArrayList<HistoryItem>> getHashMap(String key) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(MAP_SHARE_PREFERENCES, 0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key,"");
        java.lang.reflect.Type type = new TypeToken<HashMap<String, ArrayList<HistoryItem>>>(){}.getType();
        HashMap<String, ArrayList<HistoryItem>> obj = gson.fromJson(json, type);
        return obj;
    }
}

package com.gacon.julien.moodtracker4.models.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.gacon.julien.moodtracker4.models.Json.HistoryItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


public class MapSharedPref {

    public static final String MAP_SHARE_PREFERENCES = "MAP_SHARE_PREFERENCES"; // key of SharedPreferences context
    public static final String MAP = "MAP"; // key of shared preferences mood list
    private static Context context; // get context
    private Map<String, ArrayList<HistoryItem>> hashList;

    public MapSharedPref(Context context) {
        this.context = context;
    }

    public Map<String, ArrayList<HistoryItem>> getHashList() {
        return hashList;
    }

    public void setHashList(Map<String, ArrayList<HistoryItem>> hashList) {
        this.hashList = hashList;
    }

    /**
     *     Save and get HashMap in SharedPreference

     */

    public void saveHashMap() {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(MAP_SHARE_PREFERENCES, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        if (sharedPreferences != null){
            String json = gson.toJson(hashList);
            editor.putString(MAP,json);
            editor.commit();
        }

        /*
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(key,json);
        editor.apply();     // This line is IMPORTANT !!!
        */
    }

    public Map<String, ArrayList<HistoryItem>> getHashMap() {
        //Map<String, ArrayList<HistoryItem>> outputMap = new TreeMap<String, ArrayList<HistoryItem>>();
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(MAP_SHARE_PREFERENCES, 0);
        Gson gson = new Gson();
        /*try {
            if (sharedPreferences != null){
                String json = sharedPreferences.getString(MAP, (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(json);
                Iterator<String> keysItr = jsonObject.keys();
                while (keysItr.hasNext()) {
                    String key = keysItr.next();
                    java.lang.reflect.Type type = new TypeToken<HashMap<String, ArrayList<HistoryItem>>>(){}.getType();
                    outputMap = gson.fromJson(json, type);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*Gson gson = new Gson();
                */
        String json = sharedPreferences.getString(MAP,"");
        java.lang.reflect.Type type = new TypeToken<HashMap<String, ArrayList<HistoryItem>>>(){}.getType();
        HashMap<String, ArrayList<HistoryItem>> obj = gson.fromJson(json, type);
        return obj;
    }
}

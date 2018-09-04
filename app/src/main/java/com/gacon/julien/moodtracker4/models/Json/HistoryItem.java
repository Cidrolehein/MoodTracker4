package com.gacon.julien.moodtracker4.models.Json;

import com.google.gson.annotations.SerializedName;

/**
 * Items of History Activity
 */

// HistoryItem class
public class HistoryItem {

    // HistoryItem Variables
    @SerializedName("mood image")
    private int mImageResource; // mood image
    @SerializedName("date")
    private String mText1; // date
    @SerializedName("comment")
    private String mText2; // comment

    // HistoryItem constructor
    public HistoryItem(int imageResource, String text1, String text2) {
        mImageResource = imageResource; // mood image
        mText1 = text1; // date
        mText2 = text2; // comment
    } // end of HistoryItem constructor

    // Getters
    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

    // end of getters

}// end of HistoryItem class

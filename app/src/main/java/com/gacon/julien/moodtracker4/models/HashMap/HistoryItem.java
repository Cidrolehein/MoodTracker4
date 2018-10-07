package com.gacon.julien.moodtracker4.models.HashMap;

import com.google.gson.annotations.SerializedName;

/**
 * Items of History Activity
 */

// HistoryItem class
public class HistoryItem {

    // HistoryItem Variables
    @SerializedName("date")
    private String mText1; // date
    @SerializedName("comment")
    private String mText2; // comment
    @SerializedName("Image color")
    private int mImageColor; // image color
    @SerializedName("Mood Index")
    private int mCurrentMood;
    @SerializedName("height")
    private int mHeight;
    @SerializedName("Width")
    private int mWidth;

    // HistoryItem constructor
    public HistoryItem(String text1, String text2, int imageColor, int currentMood, int height, int width) {
        mText1 = text1; // date
        mText2 = text2; // comment
        mImageColor = imageColor; // image color
        mCurrentMood = currentMood;
        mHeight = height; // height relative layout
        mWidth = width; // width relative layout
    } // end of HistoryItem constructor

    // Getters

    public int getCurrentMood() {
        return mCurrentMood;
    }

    public int getHeight() {
        return mHeight;
    }

    public int getWidth() {
        return mWidth;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

    public int getImageColor() {
        return mImageColor;
    }

    // end of getters

}// end of HistoryItem class

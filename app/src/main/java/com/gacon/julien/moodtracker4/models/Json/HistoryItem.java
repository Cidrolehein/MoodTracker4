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
    @SerializedName("Image color")
    private int mImageColor; // image color
    @SerializedName("height")
    private int mHeight;
    @SerializedName("Width")
    private int mWidth;

    // HistoryItem constructor
    public HistoryItem(int imageResource, String text1, String text2, int imageColor, int height, int width) {
        mImageResource = imageResource; // mood image
        mText1 = text1; // date
        mText2 = text2; // comment
        mImageColor = imageColor; // image color
        mHeight = height; // height relative layout
        mWidth = width; // width relative layout
    } // end of HistoryItem constructor

    // Getters
    public int getHeight() {
        return mHeight;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getImageResource() {
        return mImageResource;
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

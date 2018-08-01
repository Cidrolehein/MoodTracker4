package com.gacon.julien.moodtracker4.models.Json;

import com.google.gson.annotations.SerializedName;

public class Items {
    @SerializedName("Date")
    private String mDate;
    @SerializedName("Mood")
    private String mMood;
    @SerializedName("Comment")
    private String mComment;

    public Items(String date, String mood, String comment) {
        mDate = date;
        mMood = mood;
        mComment = comment;
    }
}

package com.gacon.julien.moodtracker4.models;
import com.google.gson.annotations.SerializedName;

public class MoodAndCommentItem {
    private String mDate;
    private String mMood;
    private String mComment;

    public MoodAndCommentItem(String mood) {
        //mDate = date;
        mMood = mood;
        //mComment = comment;
    }

    @SerializedName("Date")
    public String getDate() {
        return mDate;
    }

    @SerializedName("Moods")
    public String getMood() {
        return mMood;
    }

    public void setMood(String mood) {
        mMood = mood;
    }

    @SerializedName("Comment")
    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

}
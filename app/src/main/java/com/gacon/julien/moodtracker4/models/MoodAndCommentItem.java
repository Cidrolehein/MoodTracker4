package com.gacon.julien.moodtracker4.models;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoodAndCommentItem {
    @SerializedName("Date")
    private String mDate;
    @SerializedName("Mood")
    private String mMood;
    @SerializedName("Comment")
    private String mComment;
    @SerializedName("Graph data")
    private List<GraphData> mGraphData;

    public MoodAndCommentItem(String mood, String date, String comment, List<GraphData> graphData) {
        mDate = date;
        mMood = mood;
        mComment = comment;
        mGraphData = graphData;
    }

    public String getDate() {
        return mDate;
    }

    public String getMood() {
        return mMood;
    }

    public void setMood(String mood) {
        mMood = mood;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

}
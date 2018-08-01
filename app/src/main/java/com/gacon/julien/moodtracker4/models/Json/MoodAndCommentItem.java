package com.gacon.julien.moodtracker4.models.Json;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoodAndCommentItem {
    @SerializedName("items")
    private List<Items> mItems;
    @SerializedName("Graph data")
    private List<GraphData> mGraphData;

    public MoodAndCommentItem(List<GraphData> graphData, List<Items> items) {
        mGraphData = graphData;
        mItems = items;
    }

}
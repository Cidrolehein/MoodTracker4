package com.gacon.julien.moodtracker4.models;

import com.google.gson.annotations.SerializedName;

public class GraphData {

    @SerializedName("Bar size")
    private Float mxData;
    @SerializedName("Type of mood")
    private String myData;

    public GraphData(Float xData, String yData) {
        mxData = xData;
        myData = yData;
    }

}

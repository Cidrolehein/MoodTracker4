package com.gacon.julien.moodtracker4.models.HashMap;

import com.gacon.julien.moodtracker4.models.Json.HistoryItem;

public class GeneralItem extends ListItem {

    private HistoryItem historyItemArray;

    public HistoryItem gethistoryItemArray() {
        return historyItemArray;
    }

    public void sethistoryItemArray(HistoryItem historyItemArray) {
        this.historyItemArray = historyItemArray;
    }

    @Override
    public int getType() {
        return TYPE_GENERAL;
    }


}

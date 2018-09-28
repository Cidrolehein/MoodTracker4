package com.gacon.julien.moodtracker4.models.HashMap;

public abstract class ListItem {

    public static final int TYPE_DATE = 0;
    public static final int TYPE_GENERAL = 1;

    abstract public int getType();
}

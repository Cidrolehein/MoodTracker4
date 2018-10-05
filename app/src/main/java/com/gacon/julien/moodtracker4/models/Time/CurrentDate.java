package com.gacon.julien.moodtracker4.models.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CurrentDate {

    private String strTime = "";

    public String getTime() {
        SimpleDateFormat mTime = new SimpleDateFormat("yyyy-MM-dd");
        strTime = mTime.format(Calendar.getInstance().getTime());
        return strTime;
    }

}


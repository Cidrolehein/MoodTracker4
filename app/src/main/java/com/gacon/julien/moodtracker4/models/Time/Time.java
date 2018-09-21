package com.gacon.julien.moodtracker4.models.Time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

    private final SimpleDateFormat timestamp;
    private final String message;

    public Time(SimpleDateFormat timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public SimpleDateFormat getTimestamp() {
        return timestamp;
    }

}

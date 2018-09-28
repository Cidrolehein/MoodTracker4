package com.gacon.julien.moodtracker4.models.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CurrentDate {

    private String strTime = "";

    public String getTime() {
        SimpleDateFormat mTime = new SimpleDateFormat("dd-MM-yyyy");
        strTime = mTime.format(Calendar.getInstance().getTime());
        return strTime;
    }

public String incrementDate(int i) {
        String dateInString = this.getTime();  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
    try {
        c.setTime(sdf.parse(dateInString));
    } catch (ParseException e) {
        e.printStackTrace();
    }
    c.add(Calendar.DATE, i);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        return dateInString;
}

}


package com.gacon.julien.moodtracker4.models.Time;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CurrentDate {

    private String strTime = "";
    private String moodDate;

    public String getTime() {
        SimpleDateFormat mTime = new SimpleDateFormat("yyyy-MM-dd");
        strTime = mTime.format(Calendar.getInstance().getTime());
        return strTime;
    }

    public String compareDate(String moodDate) {
        SimpleDateFormat mTime = new SimpleDateFormat("yyyy-MM-dd");
        String day = "";
        long diff;
        int diffDays = 0;

        getTime();

        try {
            String format = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date dateObj1 = sdf.parse(moodDate + " ");
            Date dateObj2 = sdf.parse(this.strTime + " ");
            DecimalFormat crunchifyFormatter = new DecimalFormat("###,###");
            diff = dateObj2.getTime() - dateObj1.getTime();
            diffDays = (int) (diff / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (moodDate.compareTo(this.strTime) == 0) {
            day = "Aujourd'hui";
        } else if (moodDate.compareTo(this.strTime) < 0) {

            if (diffDays < 7) {
                day = "Il y a " + diffDays + " jours";
            } else if (diffDays == 7) {
                day = "Il y a une semaine";
            }
            else if (diffDays > 7) {
                day = "Il y a plus d'une semaine";
            }

        } else if (moodDate.compareTo(this.strTime) > 0) {
            day = "Après";
        }

        return day;
    }

}


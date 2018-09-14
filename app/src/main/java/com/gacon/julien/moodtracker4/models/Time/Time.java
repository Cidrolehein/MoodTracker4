package com.gacon.julien.moodtracker4.models.Time;

import android.content.Context;

import java.util.Calendar;

public class Time {

    // Variables
    private int currentY, currentM, currentD;
    private int inBetweenDays;
    Context context;

    // constructor
    public Time(Context context) {
        this.context = context;
    }

    TimeSharedPreferences timeSharedPref = new TimeSharedPreferences(context);

    private int inBetweenDays() {

        //Get Current Time
        currentY = Calendar.getInstance().get(Calendar.YEAR);
        currentM = Calendar.getInstance().get(Calendar.MONTH);
        currentD = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        //Get Saved Time
        int savedYear = timeSharedPref.getYear();
        int savedMonth = timeSharedPref.getMonth();
        int savedDay = timeSharedPref.getDay();

        if (savedYear == currentY && savedMonth == currentM){
            return currentD - savedDay;

        } else if ((currentD < 7) && (currentY - savedYear <= 1)
                && ((currentM - savedMonth)==1) || (savedMonth==12 && currentM==0)) {
            int monthNbOfDays = 0;
            switch(savedMonth) {
                case 0: case 2 : case 4 : case 6 : case 7 : case 9 : case 11: monthNbOfDays = 31; break;
                case 3 : case 5 : case 8 : case 10 : monthNbOfDays = 30; break;
                case 1 : //February
                    if((savedYear % 4 == 0)&&((savedYear%100 !=0)||(savedYear %400 == 0)))
                        monthNbOfDays = 29;
                    else
                        monthNbOfDays = 28;
                    break;
            }
            inBetweenDays = currentD + (monthNbOfDays-savedDay);
        } else{
            inBetweenDays = 7;
        }

        return inBetweenDays;
    }


}

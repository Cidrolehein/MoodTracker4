package com.gacon.julien.moodtracker4.models.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MockBackend {

    private static final ArrayList<Time> MOCK_DATA = new ArrayList<>();
    private static final ArrayList<Time> MOCK_DATA_ISO = new ArrayList<>();

    private static Date date(String string) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.FRANCE).parse(string);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static Date dateIso(String string){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat.parse(string);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static ArrayList<Time> loadComments() {
        return MOCK_DATA;
    }

    public static ArrayList<Time> loadIsoComments() {
        return MOCK_DATA_ISO;
    }
}

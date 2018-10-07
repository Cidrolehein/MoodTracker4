package com.gacon.julien.moodtracker4.controllers.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.gacon.julien.moodtracker4.R;
import com.gacon.julien.moodtracker4.models.SharedPreferences.MySharedPreferences;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.gacon.julien.moodtracker4.models.SharedPreferences.MySharedPreferences.MOOD_LIST;
import static com.gacon.julien.moodtracker4.models.SharedPreferences.MySharedPreferences.SHARE_PREFERENCES;

/********************************************************************************
 * MoodTracker by Julien Gacon for OpenClassRooms - 2018
 * Graph Activity
 ********************************************************************************/

public class GraphActivity extends AppCompatActivity {

    /********************************************************************************
     * Graph Activity variables
     ********************************************************************************/

    private PieChart mPieChart;
    private MySharedPreferences mySharedPref;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private int color, currentMood;
    private int size;
    private int[] yData = {0, 0, 0, 0, 0};
    private String[] xData = {"bad mood", "disappointed mood", "normal mood", "happy mood"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        mySharedPref = new MySharedPreferences(this);
        mySharedPref.loadData();

        loadData();

        mPieChart = (PieChart) findViewById(R.id.chart);

        mPieChart.setRotationEnabled(true);
        mPieChart.setCenterText("Mes humeurs de la semaine");
        mPieChart.setDrawEntryLabels(true);
        mPieChart.getDescription().setText("Humeurs");

        addDataSet();
    }

    private void loadData () {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PREFERENCES, MODE_PRIVATE);
        String json = sharedPreferences.getString(MOOD_LIST, null);

        try {
            jsonArray = new JSONArray(json);
            for (int i = 1; i < 8; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
                color = jsonObject.getInt("Image color");
            currentMood = jsonObject.getInt("Mood Index");
                switch (currentMood) {
                    case 0:
                        yData[0]++;
                        break;
                    case 1:
                        yData[1]++;
                        break;
                    case 2:
                        yData[2]++;
                        break;
                    case 3:
                        yData[3]++;
                        break;
                    case 4:
                        yData[4]++;
                        break;
                }
            }
            size = jsonArray.length();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addDataSet() {

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        try {
            for (int i = 0; i < size; i++) {
                yEntrys.add(new PieEntry(yData[i], i));
                xEntrys.add(xData[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        PieDataSet dataSet = new PieDataSet(yEntrys, "Moods label");

        // colors
        dataSet.setColors(getResources().getIntArray(R.array.colorPagesViewPager));

        dataSet.setDrawValues(false);

        PieData pieData = new PieData(dataSet);
        mPieChart.setData(pieData);
        mPieChart.invalidate();

    }
}

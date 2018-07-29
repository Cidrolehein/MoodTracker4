package com.gacon.julien.moodtracker4.controllers.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.gacon.julien.moodtracker4.R;
import com.gacon.julien.moodtracker4.models.MoodAndCommentItem;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.gacon.julien.moodtracker4.controllers.activities.MainActivity.MOOD_LIST;
import static com.gacon.julien.moodtracker4.controllers.activities.MainActivity.SHARE_PREFERENCES;

public class HistoryActivity extends AppCompatActivity {

    // data
    private ArrayList mMoodListItems;
    private int mPosition;
    SharedPreferences sharedPreferences;
    Gson gson;

    // graph
    HorizontalBarChart mChart;
    private static String GRAPH_TAG = "GRAPH_TAG";
    private float[] xPosition = {};
    private String[] ySize = {"mood", "mood"};

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        loadData();

        //buildRecyclerView();
/*
        mChart = (HorizontalBarChart) findViewById(R.id.chart);

        mChart.animateXY(2000, 2000);

        // remove grid lines
        XAxis xl = mChart.getXAxis();
        xl.setDrawGridLines(false);

        YAxis yl = mChart.getAxisLeft();
        yl.setDrawGridLines(false);

        YAxis yl2 = mChart.getAxisRight();
        yl2.setDrawGridLines(false);

        addDataSet();
*/
       /* Data data = new Data(this);
        String Date = data.getMoodPosition("comment");
        String Mood = data.getComment("mood");
        String Comment = data.getDate("time");
        mMoodListItems.add(mPosition, new MoodAndCommentItem(Date, Mood, Comment));
        mAdapter.notifyItemInserted(mPosition);
        saveDataToGson();*/

    }

    /*public void buildRecyclerView () {

        //mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new HistoryAdapter(mMoodListItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }*/

    private void loadData() {

        Gson gson = new Gson();

        if (mMoodListItems == null) {
            mMoodListItems = new ArrayList<>();
        }
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PREFERENCES, MODE_PRIVATE);
        String json = sharedPreferences.getString(MOOD_LIST, null);
        Type type = new TypeToken<ArrayList<MoodAndCommentItem>>() {
        }.getType();
        mMoodListItems = gson.fromJson(json, type);

    }

    private void addDataSet() {

        loadData();



        int size = mMoodListItems.size();

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        ArrayList<Float> floats = new ArrayList<Float>();

        for (int i = 0; i < size; i ++){

            float floatMood = floats.get(i);

            barEntries.add(new BarEntry(i, floatMood));
        }


/*
        // graph
        ArrayList<String> yEntrys = new ArrayList<String>();
        ArrayList<BarEntry> xEntrys = new ArrayList<>();

        System.out.println(size);

            for (int i = 0; i < size; i++){
                xEntrys.add(new BarEntry(xPosition[i], i));
        }

        for (int i = 0; i < ySize.length; i++){
            yEntrys.add(ySize[i]);
            }

*/
        // create the data set
        BarDataSet dataSet = new BarDataSet(barEntries, "Mood");

        // colors
        dataSet.setColors(getResources().getIntArray(R.array.colorPagesViewPager));

        //create bar data object
        BarData barData = new BarData(dataSet);
        mChart.setData(barData);
        mChart.invalidate();

    }
}





  /*
    private void viewMoods() {
        //Inflate layout
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout container = (LinearLayout) findViewById(R.id.history_linear_layout);

        for (int i = 7; i > 0; i--) {

            View view;

            // new textview and comment button
            view = inflater.inflate(R.layout.activity_history, null);

            dailyFrameLayout (view, i);

            // Add to the container
            container.addView(view);
        }
    }

    private void getDeviceMetrics(){
        //Get Device Width and Height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        deviceWidth = displayMetrics.widthPixels;
        deviceHeight = displayMetrics.heightPixels;
    }

    private void dailyFrameLayout (View v, int numDay) {

        //Get Mood of the day [i]
        int currentMood = mData.getMoodPosition(SHARE_PREFERENCES);
        final String currentComment = mData.getComment(SHARE_PREFERENCES);
        mData.getDate(SHARE_PREFERENCES);

        //Serialize FrameLayouts, TextViews and Buttons
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.history_frameLayout);
        TextView textView = (TextView) findViewById(R.id.textView_Mood);
        Button commentButton = (Button) findViewById(R.id.ic_note_add_black);

        //Define FrameLayout metrics with device metrics * size of mood
        final double [] viewSizeMultiplier = {0.25, 0.4, 0.6, 0.8, 1};
        getDeviceMetrics();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                (int) (deviceWidth*viewSizeMultiplier[currentMood]),
                (int) deviceHeight/9);

        //Set FrameLayout
        frameLayout.setLayoutParams(params);
        frameLayout.setBackgroundColor(getResources().getIntArray(R.array.colorPagesViewPager)[currentMood]);

        //Set TextView according to Day
        textView.setText(getResources().getStringArray(R.array.dayCount)[numDay-1]);

        //Set Comment button if comment exists
        if(!currentComment.equals("")){
            //Show button + if click on button, show Comment (Toast)
            commentButton.setVisibility(ImageButton.VISIBLE);
            commentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(HistoryActivity.this, currentComment, Toast.LENGTH_SHORT).show();
                }}   );
        }else{
            commentButton.setVisibility(ImageButton.INVISIBLE);
        }
    }

    public void buildRecyclerView () {
        /*if (mMoodListItems == null) {
            mMoodListItems = new ArrayList<>();
        }
        mRecyclerView = findViewById(R.id.history_linear_layout);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new HistoryAdapter(mMoodListItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }*/

package com.gacon.julien.moodtracker4.controllers.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import com.gacon.julien.moodtracker4.R;
import com.gacon.julien.moodtracker4.adapters.PageAdapter;
import com.gacon.julien.moodtracker4.models.HashMap.DateItem;
import com.gacon.julien.moodtracker4.models.HashMap.GeneralItem;
import com.gacon.julien.moodtracker4.models.HashMap.ListItem;
import com.gacon.julien.moodtracker4.models.Json.HistoryItem;
import com.gacon.julien.moodtracker4.models.SharedPreferences.MapSharedPref;
import com.gacon.julien.moodtracker4.models.SharedPreferences.MySharedPreferences;
import com.gacon.julien.moodtracker4.models.Time.CurrentDate;
import com.gacon.julien.moodtracker4.models.Time.Time;
import com.gacon.julien.moodtracker4.models.Time.TimeSharedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/********************************************************************************
 * MoodTracker by Julien Gacon for OpenClassRooms - 2018
 * Main Activity
 ********************************************************************************/

// MainActivity class

public class MainActivity extends AppCompatActivity {

    /********************************************************************************
     * MainActivity variables
     ********************************************************************************/

    private String mNewComment = "No comment"; // default comment
    ArrayList<HistoryItem> arrayList; // history list
    private ViewPager pager; // viewpager for swiping
    MySharedPreferences sharedPreferences; // shared preferences
    private Button mNoteAddButton; // note button
    private Button mHistoryButton; // history button
    private EditText mEditTextComment; // edit text for comment
    int mCurrentPosition; // current position of mood
    private int color; // color of mood
    private double deviceWidth, deviceHeight; //Width and Height of relative layout in HistoryActivity
    private int width, height; // size of list activity
    int position; // position for list History Activity
    private int currentY, currentM, currentD; // current time
    private TimeSharedPreferences timeSharedPref;
    private MapSharedPref mapSharedPref;
    private int inBetweenDays;
    private String time;
    SimpleDateFormat mTime;
    private ArrayList<Time> timeArray;
    private ArrayList<ListItem> consolidatedList;

    /**
     * Array of mood items
     */

    // Image Mood list
    private int[] imageMoods = new int[]{
            R.drawable.smiley_sad,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_normal,
            R.drawable.smiley_happy,
            R.drawable.smiley_super_happy
    }; // end of image mood list

    /********************************************************************************
     * MainActivity Life Cycle
     ********************************************************************************/

    // MainActivity onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = new MySharedPreferences(this); // initialize SharedPreferences
        timeSharedPref = new TimeSharedPreferences(this); // time shared preferences
        mapSharedPref = new MapSharedPref(this);

        consolidatedList = new ArrayList<>();

        // Configure ViewPager and Title
        this.configureViewPagerAndTitle();

        mNoteAddButton = (Button) findViewById(R.id.ic_note_add_black);
        mHistoryButton = (Button) findViewById(R.id.ic_history_black);

        mNoteAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAlertDialog(); // comment alertDialog

            }
        }); // add comment

        // Open History Activity
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);

            } //end of onClick method
        });

    } // end of OnCreate method

    /**
     * Restart Activity
     */

    @Override
    protected void onRestart() {
        super.onRestart();

    } // end of onRestart method

    /**
     * Start Activity
     * Load data from Shared Preferences
     */

    @Override
    protected void onStart() {
        super.onStart();

            sharedPreferences.loadData(); // load data from sharedPreferences
            timeSharedPref.loadData(); // load data time

    } // end of onStart method

    /**
     * OnResume activity
     */
    @Override
    protected void onResume() {
        super.onResume();


    } // end of onResume method

    /**
     * Activity on Pause method
     * Add data to shared preferences
     */

    @Override
    protected void onPause() {
        super.onPause();

        addToList(); // add data to SharedPreferences

        //Get Current Time
        currentY = Calendar.getInstance().get(Calendar.YEAR);
        currentM = Calendar.getInstance().get(Calendar.MONTH);
        currentD = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        timeSharedPref.saveData();// time shared preferences

        // condition
        if (isFinishing()) { // user call finish to pause Activity


        } else { // user don't call finish

        } // end of condition

    } // end of onPause method

    /**
     * Activity on stop method
     * save Shared Preferences
     */

    @Override
    protected void onStop() {
        super.onStop();

        sharedPreferences.saveData(); // save shared preferences

    } // end of on stop method

    /**
     * Destroy activity
     */

    @Override
    protected void onDestroy(){
        super.onDestroy();

    } // end of onDestroy method


    /********************************************************************************
     * MainActivity methods
     ********************************************************************************/

    /**
     * View
     */

    // New vertical ViewPager with Adapter
    private void configureViewPagerAndTitle() {

        // Get ViewPager from layout
        pager = (ViewPager)findViewById(R.id.vertical_viewpager);

        // Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), this.imageMoods, getResources().getIntArray(R.array.colorPagesViewPager)) {
        });

        // Set default position
        pager.setCurrentItem(3);

    } // end of configureViewPagerAndTitle method

    // Alert Dialog for new comment
    private void createAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this); // get context

        LayoutInflater inflater = this.getLayoutInflater(); // add new layout

        View view = inflater.inflate(R.layout.layout_dialog, null); // initialize view

        mEditTextComment = view.findViewById(R.id.edit_comment); // refer view

        // view builder
        builder.setView(view)
                .setTitle("Commentaire") // title
                // add cancel button
                .setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }) // end of cancel button method
                // add OK button
                .setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mNewComment = mEditTextComment.getText().toString();

                    }
                }) // end of OK button method
                .create() // create view
                .show(); // show

    } // end of AlertDialog method

    /**
     *     Configure relative layout for history view
     */

    // configure Width and Height
    private void getDeviceMetrics(){
        //Get Device Width and Height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        deviceWidth = displayMetrics.widthPixels;
        deviceHeight = displayMetrics.heightPixels;
    } // end of getDeviceMetrics method

    /**
     * History List add new items
     */

    // add to History List
    private void addToList() {

        // ! condition
    if (arrayList == null) {
        arrayList = new ArrayList<>();
    } // end of condition

        CurrentDate cDate = new CurrentDate();
        time = cDate.getTime();

    mCurrentPosition = pager.getCurrentItem(); // mood position
    color = getResources().getIntArray(R.array.colorPagesViewPager)[mCurrentPosition]; // background color
        //Define FrameLayout metrics with device metrics * size of mood
        final double [] viewSizeMultiplier = {0.25, 0.4, 0.6, 0.8, 1};
        getDeviceMetrics();
        width = (int) (deviceWidth*viewSizeMultiplier[mCurrentPosition]);
        height = (int) (deviceHeight/7);
    arrayList = sharedPreferences.getHistoryList(); // get history list
    arrayList.add(position, new HistoryItem(time, mNewComment, color, mCurrentPosition, height, width));// add to list

        groupDataIntoHashMap(arrayList);
        mapSharedPref.saveHashMap(time, groupDataIntoHashMap(arrayList));

        sharedPreferences.setHistoryList(arrayList); // save data

    } // end of addToList method

    private HashMap<String, ArrayList<HistoryItem>> groupDataIntoHashMap(ArrayList<HistoryItem> listOfHistoryItems) {

        HashMap<String, ArrayList<HistoryItem>> groupedHashMap = new HashMap<>();

        CurrentDate cdate = new CurrentDate();

        for (HistoryItem historyItems : listOfHistoryItems) {

            String hashMapKey = cdate.getTime();

            if (groupedHashMap.containsKey(hashMapKey)) {


            } else {
                // The key is not there in the HashMap; create a new key-value pair
                ArrayList<HistoryItem> list = new ArrayList<>();
                list.add(historyItems);
                groupedHashMap.put(hashMapKey, list);
            }

        }

        return groupedHashMap;
    }

} // end of MainActivity class

/********************************************************************************
 * Thank you !
 * More information >>> juliengacon@gmail.com <<<
 ********************************************************************************/

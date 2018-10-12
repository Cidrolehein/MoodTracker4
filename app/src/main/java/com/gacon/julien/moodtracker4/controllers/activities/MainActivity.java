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
import com.gacon.julien.moodtracker4.views.adapters.PageAdapter;
import com.gacon.julien.moodtracker4.models.HashMap.HistoryItem;
import com.gacon.julien.moodtracker4.models.SharedPreferences.MySharedPreferences;
import com.gacon.julien.moodtracker4.models.Time.CurrentDate;
import java.util.ArrayList;

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
    private ArrayList<HistoryItem> arrayList; // history list
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
    private String time;

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

    @Override
    protected void onResume() {
        super.onResume();
        mNewComment = "No comment";
    }

    /**
     * Activity on Pause method
     * Add data to shared preferences
     */

    @Override
    protected void onPause() {
        super.onPause();

        addToList(); // add data to SharedPreferences

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

        sharedPreferences.loadData(); // load data from sharedPreferences

        // ! condition
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        } // end of condition

        arrayList = sharedPreferences.getHistoryList(); // get history list

        // Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), this.imageMoods, getResources().getIntArray(R.array.colorPagesViewPager)) {
        });

        // Set default position
        CurrentDate currentDate = new CurrentDate();
        if (arrayList != null && arrayList.size() != 0) {
            int currentPosition = sharedPreferences.getHistoryList().get(0).getCurrentMood();
            String timeBefore = sharedPreferences.getHistoryList().get(0).getText1();
            String timeNow = currentDate.getTime();
            if (currentDate.compareDate(timeBefore) == "Aujourd'hui") {
                pager.setCurrentItem(currentPosition);
            }else {
                pager.setCurrentItem(3);
            }
        } else {
            pager.setCurrentItem(3);
        }


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

        CurrentDate cDate = new CurrentDate();
        time = cDate.getTime();

    mCurrentPosition = pager.getCurrentItem(); // mood position
    color = getResources().getIntArray(R.array.colorPagesViewPager)[mCurrentPosition]; // background color
        //Define FrameLayout metrics with device metrics * size of mood
        final double [] viewSizeMultiplier = {0.25, 0.4, 0.6, 0.8, 1};
        getDeviceMetrics();
        width = (int) (deviceWidth*viewSizeMultiplier[mCurrentPosition]);
        height = (int) (deviceHeight/7);
    arrayList.add(position, new HistoryItem(time, mNewComment, color, mCurrentPosition, height, width));// add to list
        sharedPreferences.setHistoryList(arrayList);

    } // end of addToList method

} // end of MainActivity class

/********************************************************************************
 * Thank you !
 * More information >>> juliengacon@gmail.com <<<
 ********************************************************************************/

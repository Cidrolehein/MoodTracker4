package com.gacon.julien.moodtracker4.controllers.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.gacon.julien.moodtracker4.R;
import com.gacon.julien.moodtracker4.adapters.PageAdapter;
import com.gacon.julien.moodtracker4.models.Json.HistoryItem;
import com.gacon.julien.moodtracker4.models.SharedPreferences.MySharedPreferences;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/********************************************************************************
 * MoodTracker by Julien Gacon for OpenClassRooms - 2018
 * Main Activity
 ********************************************************************************/

/**
 * TODO : manage current position with mood items - add sound
 */

// MainActivity class

public class MainActivity extends AppCompatActivity {

    /********************************************************************************
     * MainActivity variables
     ********************************************************************************/

    private String mNewComment = "No comment"; // default comment
    private String formattedTime; // time
    ArrayList<HistoryItem> arrayList; // history list
    private ViewPager pager; // viewpager for swiping
    MySharedPreferences sharedPreferences; // shared preferences
    private Button mNoteAddButton; // note button
    private Button mHistoryButton; // history button
    private EditText mEditTextComment; // edit text for comment

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
     * data
     */

    // time
    private String getTimeFromTheSystem() {

        Locale locale = Locale.getDefault(); // get local time (phone)
        Calendar cal = new GregorianCalendar(); // initialize calendar
        cal.getInstance(locale);
        cal.getTime().toLocaleString(); // time to string

        formattedTime = cal.getTime().toLocaleString(); // variable

        return formattedTime;

    } // end of Time method

    /**
     * History List add new items
     */

    // add to History List
    private void addToList() {

        // ! condition
    if (arrayList == null) {
        arrayList = new ArrayList<>();
    } // end of condition

    getTimeFromTheSystem(); // get current time

    arrayList = sharedPreferences.getHistoryList(); // get history list
    arrayList.add(new HistoryItem(R.drawable.smiley_normal, formattedTime, mNewComment)); // add to list

    sharedPreferences.setHistoryList(arrayList); // save data

    } // end of addToList method

} // end of MainActivity class

/********************************************************************************
 * Thank you !
 * More information >>> juliengacon@gmail.com <<<
 ********************************************************************************/

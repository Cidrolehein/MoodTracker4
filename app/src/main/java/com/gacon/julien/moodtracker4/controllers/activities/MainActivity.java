package com.gacon.julien.moodtracker4.controllers.activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.gacon.julien.moodtracker4.models.MoodAndCommentItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MoodAndCommentItem> mMoodListItems;
    private int mPosition;
    private Gson gson;
    private String mNewComment = "No comment";
    private String formattedTime;
    int mCurrentPosition;

    private ViewPager pager;

    public static final String SHARE_PREFERENCES = "SHARE_PREFERENCES";
    public static final String MOOD_LIST = "MOOD_LIST";

    private Button mNoteAddButton;
    private Button mHistoryButton;
    private EditText mEditTextComment;

    //private SharedPreferences sharedPreferences;

    // Image Mood list
    private int[] imageMoods = new int[]{
            R.drawable.smiley_sad,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_normal,
            R.drawable.smiley_happy,
            R.drawable.smiley_super_happy
    };

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //3 - Configure ViewPager and Title
        this.configureViewPagerAndTitle();

        mNoteAddButton = (Button) findViewById(R.id.ic_note_add_black);
        mHistoryButton = (Button) findViewById(R.id.ic_history_black);

        mNoteAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });

        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });

    }

    /**
     * Exécuté que l'activité arrêtée via un "stop" redémarre.
     *
     * La fonction onRestart() est suivie de la fonction onStart().
     */
    @Override
    protected void onRestart() {
        super.onRestart();

    }


    /**
     * Exécuté lorsque l'activité devient visible à l'utilisateur.
     *
     * La fonction onStart() est suivie de la fonction onResume().
     */
    @Override
    protected void onStart() {
        super.onStart();

        // Récupération des anciens paramètres (shared preferences)
        {
            loadData();

        }

    }

    /**
     * Exécutée a chaque passage en premier plan de l'activité.
     * Ou bien, si l'activité passe à nouveau en premier (si une autre activité était passé en premier plan entre temps).
     *
     * La fonction onResume() est suivie de l'exécution de l'activité.
     */
    @Override
    protected void onResume() {
        super.onResume();


    }

    /**
     * La fonction onPause() est suivie :
     * - d'un onResume() si l'activité passe à nouveau en premier plan
     * - d'un onStop() si elle devient invisible à l'utilisateur
     *
     * L'exécution de la fonction onPause() doit être rapide,
     * car la prochaine activité ne démarrera pas tant que l'exécution
     * de la fonction onPause() n'est pas terminée.
     */
    @Override
    protected void onPause() {
        super.onPause();

        // Sauvegarde des paramètres
        // pour pouvoir les restaurer au prochain démarrage
        {
            addToList();

        }

        if (isFinishing()) { // "onPause, l'utilisateur à demandé la fermeture via un finish()"


        } else { // "onPause, l'utilisateur n'a pas demandé la fermeture via un finish()"

        }
    }

    /**
     * La fonction onStop() est exécutée :
     * - lorsque l'activité n'est plus en premier plan
     * - ou bien lorsque l'activité va être détruite
     *
     * Cette fonction est suivie :
     * - de la fonction onRestart() si l'activité passe à nouveau en premier plan
     * - de la fonction onDestroy() lorsque l'activité se termine ou bien lorsque le système décide de l'arrêter
     */
    @Override
    protected void onStop() {
        super.onStop();

        saveData();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

    }

    private void openDialog() {

        createAlertDialog();
    }

    // New vertical ViewPager with Adapter

    private void configureViewPagerAndTitle() {

        // 1 - Get ViewPager from layout
        pager = (ViewPager)findViewById(R.id.vertical_viewpager);

        // 2 - Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), this.imageMoods, getResources().getIntArray(R.array.colorPagesViewPager)) {
        });

        //Set default position
        pager.setCurrentItem(3);

    }

    private void createAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        mEditTextComment = view.findViewById(R.id.edit_comment);

        builder.setView(view)
                .setTitle("Commentaire")
                .setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mNewComment = mEditTextComment.getText().toString();

                    }
                })
                .create()
                .show();

    }

    private String getTimeFromTheSystem() {

        Locale locale = Locale.getDefault();
        Calendar cal = new GregorianCalendar();
        cal.getInstance(locale);
        cal.getTime().toLocaleString();

        formattedTime = cal.getTime().toLocaleString();

        return formattedTime;

    }

    private String getCurrentPosition() {

        mCurrentPosition = pager.getCurrentItem();

        // Get Current Position for Mood selected

        switch (mCurrentPosition) {
            case 0:
                return "Sad Mood";
            case 1:
                return "Disapointed Mood";
            case 2:
                return "Normal Mood";
            case 3:
                return "Happy Mood";
            case 4:
                return "Super Happy Mood";
            default:
                return null;

        }

    }

    private void addToList () {

        if (mMoodListItems == null) {
            mMoodListItems = new ArrayList<>();
        }

        String myPosition = getCurrentPosition();
        //mAdapter = new HistoryAdapter(mMoodListItems);
        mMoodListItems.add(mPosition, new MoodAndCommentItem(myPosition));
        //mAdapter.notifyItemInserted(mPosition);
    }

    private void saveData() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mMoodListItems);
        editor.putString(MOOD_LIST, json);
        editor.apply();
    }

    private void loadData() {

        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PREFERENCES, MODE_PRIVATE);
        String json = sharedPreferences.getString(MOOD_LIST, null);
        Type type = new TypeToken<ArrayList<MoodAndCommentItem>>() {
        }.getType();
        mMoodListItems = gson.fromJson(json, type);

    }

}

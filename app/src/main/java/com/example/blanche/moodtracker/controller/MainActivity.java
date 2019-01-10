package com.example.blanche.moodtracker.controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.blanche.moodtracker.R;
import com.example.blanche.moodtracker.adapters.PageAdapter;
import com.example.blanche.moodtracker.models.Mood;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static com.example.blanche.moodtracker.Fragments.FragmentPage.KEY_COMMENT;
import static com.example.blanche.moodtracker.controller.Utils.addMood;

public class MainActivity extends AppCompatActivity {

    VerticalViewPager verticalViewPager;
    private SharedPreferences preferences;
    public static final String KEY_MOOD_SELECTED = "moodSelected";
    public static final String KEY_DATE = "date";
    public static final String APP_PREFERENCES = "appPreferences";

    @Override
    protected void onStart() {
        super.onStart();
        checkTime();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);

        this.configureVerticalViewPager();

        setTheDefaultFragment();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveMood();
        saveDate();
    }

    private void saveMood() {
        //get the current fragment selected
        int currentFragment = verticalViewPager.getCurrentItem() + 1;
        //save in the preferences the current mood selected
        preferences.edit().putInt(KEY_MOOD_SELECTED, currentFragment).apply();
    }

    private void saveDate() {
        //save in the preferences the date when it has been saved
        LocalDate currentDate = LocalDate.now();
        String date = currentDate.toString();
        preferences.edit().putString(KEY_DATE, date).apply();
        System.out.println("datewhensaved = " + date);
    }

    private void configureVerticalViewPager() {
        //get the view viewPager with the id
        verticalViewPager = findViewById(R.id.activity_main_viewPager);
        //set the adapter to the view Pager
        verticalViewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.backgroundColors)) {
        });
    }

    private void setTheDefaultFragment() {
        int lastMoodSelected = preferences.getInt(KEY_MOOD_SELECTED, 0);
        //si on a déjà selectionné une humeur, alors on set le current fragment à cette humeur, sinon on affiche le fragment par défaut
        if (lastMoodSelected != 0) {
            verticalViewPager.setCurrentItem(lastMoodSelected - 1);
        } else {
            verticalViewPager.setCurrentItem(2);
        }
    }

    private void checkTime() {
        //retrieve the date saved when we saved the last mood
        String date = preferences.getString(KEY_DATE, null);
        LocalDate dateWhenSaved = LocalDate.parse(date);

        if (dateWhenSaved != null) {
            //get the current date
            LocalDate currentDate = LocalDate.now();

            System.out.println("current date = " + currentDate);
            System.out.println("pref date = " + dateWhenSaved);

            long daysBetween = ChronoUnit.DAYS.between(dateWhenSaved, currentDate);

            System.out.println("days between  = " + daysBetween);

            //retrieve the moods, depending on the numbers of days between last date saved and current date
            retrievePassedMood(daysBetween);
        }
    }

    private void setPreferencesToNull() {
        preferences.edit().putInt(KEY_MOOD_SELECTED, 0).apply();
        preferences.edit().putString(KEY_COMMENT, null).apply();
        preferences.edit().putString(KEY_DATE, null).apply();
    }

    private void retrievePassedMood(long daysBetween) {
        for (int i = 0; i < daysBetween; i++) {
            int mood = preferences.getInt(KEY_MOOD_SELECTED, 0);
            String comment = preferences.getString(KEY_COMMENT, null);

            Mood newMood = new Mood(comment, mood);
            addMood(this, newMood);

            setPreferencesToNull();
        }
    }
}




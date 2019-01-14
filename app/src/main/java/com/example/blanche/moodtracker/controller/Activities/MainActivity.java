package com.example.blanche.moodtracker.controller.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.blanche.moodtracker.R;
import com.example.blanche.moodtracker.adapters.PageAdapter;
import com.example.blanche.moodtracker.controller.VerticalViewPager;
import com.example.blanche.moodtracker.models.Mood;
import java.util.Calendar;
import java.util.TimeZone;
import static com.example.blanche.moodtracker.controller.Fragments.FragmentPage.KEY_COMMENT;
import static com.example.blanche.moodtracker.controller.Utils.addMood;

public class MainActivity extends AppCompatActivity {

    VerticalViewPager verticalViewPager;
    private SharedPreferences preferences;
    public static final String KEY_MOOD_SELECTED = "moodSelected";
    public static final String KEY_DATE = "date";
    public static final String APP_PREFERENCES = "appPreferences";
    public static final long DAY_IN_MILLIS = 24*60*60*1000;

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

    /**
     * Save the mood selected in the preferences (KEY_MOOD_SELECTED)
     */
    private void saveMood() {
        //get the current fragment selected and add one because the moodtag begins to 1, not 0
        int currentFragment = verticalViewPager.getCurrentItem() + 1;
        //save in the preferences the current mood selected
        preferences.edit().putInt(KEY_MOOD_SELECTED, currentFragment).apply();
    }

    /**
     * Save the date in the preferences (KEY_DATE)
     */
    private void saveDate() {
        TimeZone timeZone = TimeZone.getDefault();
        long timeWhenSaved = Calendar.getInstance().getTimeInMillis();
        timeWhenSaved = timeWhenSaved+timeZone.getDSTSavings();
        preferences.edit().putLong(KEY_DATE, timeWhenSaved).apply();
    }

    private void configureVerticalViewPager() {
        //get the view viewPager with the id
        verticalViewPager = findViewById(R.id.activity_main_viewPager);
        //set the adapter to the view Pager
        verticalViewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.backgroundColors)) {
        });
    }

    /**
     * Determines which fragment to display when we launch the app, depending on if a mood is already saved or not
     */
    private void setTheDefaultFragment() {
        int lastMoodSelected = preferences.getInt(KEY_MOOD_SELECTED, 0);
        if (lastMoodSelected != 0) {
            verticalViewPager.setCurrentItem(lastMoodSelected - 1);
        } else {
            verticalViewPager.setCurrentItem(1);
        }
    }

    /**
     * Compares current date to last saved date to know how many days passed between two dates.
     * if it s the same day, doesn't create new mood
     * if one day or more passed, creates one mood for each day passed, and adds it to the array of mood in the preferences
     */
    private void checkTime() {
        //retrieve the date saved when we saved the last mood
        long timeWhenSaved = preferences.getLong(KEY_DATE, 0);;

        if (timeWhenSaved != 0) {
            //get the current time
            long currentTime = Calendar.getInstance().getTimeInMillis();
            TimeZone timeZone = TimeZone.getDefault();
            currentTime = currentTime+timeZone.getDSTSavings();
            //get the time when we saved at midnight (at the beginning of the day)
            long timeRemain = timeWhenSaved % DAY_IN_MILLIS;
            long atStartOfTheDay = timeWhenSaved - timeRemain;
            //see how many days passed between current time and last day we saved mood(at midnight)
            long timeBetween = currentTime - atStartOfTheDay;
            double nbrOfDay = Math.floor(timeBetween/DAY_IN_MILLIS);
            if(timeBetween >= DAY_IN_MILLIS) {
                if(nbrOfDay != 0) {
                    //for each day passed we create a mood
                    for(int i = 0; i < nbrOfDay; i ++) {
                        retrievePassedMood();
                    }
                    setTheDefaultFragment();
                }
            }
        }
    }

    private void setPreferencesToNull() {
        preferences.edit().putInt(KEY_MOOD_SELECTED, 0).apply();
        preferences.edit().putString(KEY_COMMENT, null).apply();
        preferences.edit().putLong(KEY_DATE, 0).apply();
    }

    private void retrievePassedMood() {
            int mood = preferences.getInt(KEY_MOOD_SELECTED, 0);
            String comment = preferences.getString(KEY_COMMENT, null);
            Mood newMood = new Mood(comment, mood);
            addMood(this, newMood);

            setPreferencesToNull();
    }
}




package com.example.blanche.moodtracker.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.blanche.moodtracker.R;
import com.example.blanche.moodtracker.adapters.PageAdapter;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    VerticalViewPager verticalViewPager;
    private SharedPreferences preferences;
    private int currentFragment;
    public static final String KEY_MOOD_SELECTED = "moodSelected";
    public static final String APP_PREFERENCES = "appPreferences";





    private PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);

        //start the alarm
        //setTheAlarm();

        this.configureVerticalViewPager();

        setTheDefaultFragment();



    }



    @Override
    protected void onStop() {
        super.onStop();
        //get the current fragment selected
        currentFragment = verticalViewPager.getCurrentItem()+1;
        //save in the preferences the current mood selected
        preferences.edit().putInt(KEY_MOOD_SELECTED, currentFragment).apply();
    }



    private void configureVerticalViewPager() {
        //get the view viewPager with the id
        verticalViewPager = findViewById(R.id.activity_main_viewPager);
        //set the adapter to the view Pager
        verticalViewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.backgroundColors)) {
        });
    }

    private void setTheAlarm() {
        //retreive a pending intent that will perform a broadcast
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
     }

    private void setTheDefaultFragment() {
        int lastMoodSelected = preferences.getInt(KEY_MOOD_SELECTED, 0);

        //si on a déjà selectionné une humeur, alors on set le current fragment à cette humeur, sinon on affiche le fragment par défaut
        if(lastMoodSelected != 0) {
            verticalViewPager.setCurrentItem(lastMoodSelected);
        } else {
            verticalViewPager.setCurrentItem(2);
        }
    }


}


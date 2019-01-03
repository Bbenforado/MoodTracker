package com.example.blanche.moodtracker.controller;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.blanche.moodtracker.R;
import com.example.blanche.moodtracker.adapters.PageAdapter;
import com.example.blanche.moodtracker.controller.VerticalViewPager;

import java.util.Calendar;
import java.util.Date;

import static android.text.format.DateUtils.DAY_IN_MILLIS;

public class MainActivity extends AppCompatActivity {

    VerticalViewPager verticalViewPager;
    private SharedPreferences preferences;
    private int currentFragment;
    public static final String KEY_CURRENT_FRAGMENT = "currentFragment";
    public static final String KEY_TIME = "time";
    public static final String KEY_DAY_ONE = "dayOne";

    int i = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getPreferences(MODE_PRIVATE);


        this.configureVerticalViewPager();


        //si les preference avec l humeur selectionee sont remplies alors on set le current item à la derniere humeur selectionee

        //set the default fragment to the happy smiley
        verticalViewPager.setCurrentItem(1);
    }


    private void configureVerticalViewPager() {
        //get the view viewPager with the id
        verticalViewPager = findViewById(R.id.activity_main_viewPager);
        //set the adapter to the view Pager
        verticalViewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.backgroundColors)) {
        });

    }


    @Override
    protected void onStop() {
        super.onStop();
        //get the last fragment selected
        currentFragment = verticalViewPager.getCurrentItem();

        Calendar calendar = Calendar.getInstance();

        //si le temps écoulé entre le current time et le dernier temps enregistré est plus grand que le temps d'une journée en mllis
        if (calendar.getTimeInMillis() - preferences.getLong(KEY_TIME, 0) > DAY_IN_MILLIS) {
            //alors crée une nouvelle préférence qui enregistre la derniere humeur
            preferences.edit().putInt("day" + i, currentFragment).apply();
            //dans key time on enregistre le dernier temps
            preferences.edit().putLong(KEY_TIME, calendar.getTimeInMillis()).apply();
            i++;
        } else {
            preferences.edit().putInt(KEY_DAY_ONE, currentFragment).apply();
            preferences.edit().putLong(KEY_TIME, calendar.getTimeInMillis()).apply();
        }
        System.out.println("i = " + i);
        System.out.println("day one = " + preferences.getInt("day 1", 0));
        System.out.println("day two = " + preferences.getInt("day 2", 0));
        System.out.println("day three = " + preferences.getInt("day 3", 0));
        System.out.println("DAY_ONE = " + preferences.getInt(KEY_DAY_ONE, 0));

    }


}


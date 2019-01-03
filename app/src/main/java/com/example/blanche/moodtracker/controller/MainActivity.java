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

public class MainActivity extends AppCompatActivity {

    VerticalViewPager verticalViewPager;
    private SharedPreferences preferences;
    private int currentFragment;
    public static final String KEY_CURRENT_FRAGMENT = "currentFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getPreferences(MODE_PRIVATE);


        this.configureVerticalViewPager();
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

    //private void setTheWeeksMood() {
    // for (int i = 1; i <= 7; i++) {


    //  }

    @Override
    protected void onStop() {
        super.onStop();
        //get the last fragment selected
        currentFragment = verticalViewPager.getCurrentItem();
        System.out.println("currentfragment is " + currentFragment);

        //put in it the preferences
        preferences.edit().putInt(KEY_CURRENT_FRAGMENT, currentFragment).apply();

        System.out.println(" preferences" + preferences.getInt(KEY_CURRENT_FRAGMENT, 0));


    }

    //faire une boucle qui fait 7 tours et pour chaque tour
    //chaque tour doit faire 24h
    //à chaque tour on crée une nouvelle preference DAY_1 etc et on met la valeur de key current fragment dans une autre shared preference day1


}


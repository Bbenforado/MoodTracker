package com.example.blanche.moodtracker.controller;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.blanche.moodtracker.R;
import com.example.blanche.moodtracker.adapters.PageAdapter;
import com.example.blanche.moodtracker.controller.VerticalViewPager;

public class MainActivity extends AppCompatActivity {

    //PageAdapter pageAdapter;
    VerticalViewPager verticalViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureVerticalViewPager();
    }


    private void configureVerticalViewPager() {
        //get the view viewPager with the id
        verticalViewPager = findViewById(R.id.activity_main_viewPager);
        //set the adapter to the view Pager
        verticalViewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.backgroundColors)) {
        });

    }
}

package com.example.blanche.moodtracker;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.blanche.moodtracker.adapters.PageAdapter;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureViewPager();
    }


    private void configureViewPager() {
        //get the view viewPager with the id
        ViewPager viewPager = findViewById(R.id.activity_main_viewPager);
        //set the adapter to the view Pager
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.backgroundColors)) {
        });

    }
}

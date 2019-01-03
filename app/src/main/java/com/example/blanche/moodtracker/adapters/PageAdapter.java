package com.example.blanche.moodtracker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.blanche.moodtracker.Fragments.FragmentPage;
import com.example.blanche.moodtracker.R;

public class PageAdapter extends FragmentPagerAdapter {

    private static int NUMBER_OF_PAGES = 5;

    private int currentPosition;

    //array of color
    private int[] backgroundColorsList;

    //array of images
    private int[] smileysImagesList = {
            R.drawable.smiley_super_happy,
            R.drawable.smiley_happy,
            R.drawable.smiley_normal,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_sad
    };


    //builder
    public PageAdapter(FragmentManager fragmentManager, int[] backgroundColorsList) {
        super(fragmentManager);
        this.backgroundColorsList = backgroundColorsList;
    }


    @Override
    public Fragment getItem(int position) {
        return FragmentPage.newInstance(position, this.backgroundColorsList[position], this.smileysImagesList[position]);


    }


    //number of pages to show
    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }






}

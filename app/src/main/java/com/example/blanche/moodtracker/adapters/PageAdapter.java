package com.example.blanche.moodtracker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.blanche.moodtracker.controller.Fragments.FragmentPage;
import com.example.blanche.moodtracker.R;

public class PageAdapter extends FragmentPagerAdapter {

    private static int NUMBER_OF_PAGES = 5;

    //array of color
    private int[] backgroundColorsArray;

    //array of images
    private int[] smileysImagesArray = {
            R.drawable.smiley_super_happy,
            R.drawable.smiley_happy,
            R.drawable.smiley_normal,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_sad
    };
    private String[] contentToShare = {
            "J'ai trop la pêche!",
            "Tout roule!",
            "Rien de fou aujourd'hui...",
            "ça va moyen-moyen...",
            "Trop déprimé(e) aujourd'hui..."
    };

    //builder
    public PageAdapter(FragmentManager fragmentManager, int[] backgroundColorsArray) {
        super(fragmentManager);
        this.backgroundColorsArray = backgroundColorsArray;
    }

    /**
     * Return one fragment to display
     * with one background color of the background color array
     * and one image of the image array
     * @param position
     * @return the fragment to display
     */
    @Override
    public Fragment getItem(int position) {
        return FragmentPage.newInstance(position, this.backgroundColorsArray[position], this.smileysImagesArray[position], this.contentToShare[position]);
    }

    /**
     * Number of pages to create
     * @return the number of pages we want
     */
    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }
}

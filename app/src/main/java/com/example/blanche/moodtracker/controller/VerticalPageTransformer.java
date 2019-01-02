package com.example.blanche.moodtracker.controller;

import android.support.v4.view.ViewPager;
import android.view.View;

public class VerticalPageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View view, float position) {
        final int pageWidth = view.getWidth();
        final int pageHeight = view.getHeight();
        if (position < -1) {
            view.setAlpha(0);
        } else if (position <= 1) {
            view.setAlpha(1);
            view.setTranslationX(pageWidth * -position);
            float yPosition = position * pageHeight;
            view.setTranslationY(yPosition);
        } else {
            view.setAlpha(0);
        }
    }
}


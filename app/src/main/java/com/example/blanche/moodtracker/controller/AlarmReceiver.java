package com.example.blanche.moodtracker.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.blanche.moodtracker.models.Mood;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.blanche.moodtracker.Fragments.FragmentPage.KEY_COMMENT;
import static com.example.blanche.moodtracker.controller.MainActivity.APP_PREFERENCES;
import static com.example.blanche.moodtracker.controller.MainActivity.KEY_MOOD_SELECTED;


public class AlarmReceiver extends BroadcastReceiver {


    Utils utils;

    @Override
    public void onReceive(Context context, Intent intent) {

        //recurring task here, will happen every 24h at midnight
        utils = new Utils();

        SharedPreferences preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        int currentMoodSelected = preferences.getInt(KEY_MOOD_SELECTED, 0);
        String currentComment = preferences.getString(KEY_COMMENT, null);
        Mood currentMood;
        currentMood = new Mood(currentComment, currentMoodSelected);

        //on le met dans un tableau de mood
        utils.addMood(context, currentMood);

        //on remet les preferences à zero
        preferences.edit().putInt(KEY_MOOD_SELECTED, 0).apply();
        preferences.edit().putString(KEY_COMMENT, null).apply();

    }


}

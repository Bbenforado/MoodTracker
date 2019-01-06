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

    public static final String KEY_ARRAY_MOOD = "arrayOfMood";

    @Override
    public void onReceive(Context context, Intent intent) {

        //recurring task here, will happen every 24h at midnight


        //on récupere la currentmood (key_mood_selected) et le comment
   //     SharedPreferences preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    //    int currentMoodSelected = preferences.getInt(KEY_MOOD_SELECTED, 0);
     //   String currentComment = preferences.getString(KEY_COMMENT, null);
        //on crée la mood avec ces deux attributs

       // Mood currentMood = new Mood(currentComment, currentMoodSelected);

        //on le met dans un tableau de mood
       // addMood(context, currentMood);


    }


}

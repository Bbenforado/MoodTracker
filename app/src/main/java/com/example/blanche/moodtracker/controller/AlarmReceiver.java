package com.example.blanche.moodtracker.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.blanche.moodtracker.models.Mood;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.blanche.moodtracker.Fragments.FragmentPage.KEY_COMMENT;
import static com.example.blanche.moodtracker.controller.MainActivity.APP_PREFERENCES;
import static com.example.blanche.moodtracker.controller.MainActivity.KEY_DATE;
import static com.example.blanche.moodtracker.controller.MainActivity.KEY_MOOD_SELECTED;
import static com.example.blanche.moodtracker.controller.Utils.addMood;


public class AlarmReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

    }
}

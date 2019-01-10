package com.example.blanche.moodtracker.controller;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.blanche.moodtracker.models.Mood;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static com.example.blanche.moodtracker.controller.MainActivity.APP_PREFERENCES;

public class Utils {

    public static final String KEY_ARRAY_MOOD = "arrayOfMood";


    //permet d'ajouter une nouvelle humeur
    public static void addMood(Context context, Mood currentMood) {

        //on récupère le tableau
        Mood arrayOfMood[] = retrieveArrayOfMood(context);

        //déplacer les éléments
        for(int i = 0; i < arrayOfMood.length-1; i++) {
            arrayOfMood[i] = arrayOfMood[i+1];
        }
        arrayOfMood[6] = currentMood;

        //sauvegarder le tableau dans sharedpreferences

        //on tranforme le tableau en string grace à gson
        Gson gson = new Gson();
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ARRAY_MOOD, gson.toJson(arrayOfMood)).apply();
    }



    //permet de récuperer le tableau des humeurs
    public static Mood[] retrieveArrayOfMood(Context context) {
        Mood[] arrayOfMood;
        Gson gson = new Gson();

        SharedPreferences preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String json = preferences.getString(KEY_ARRAY_MOOD, null);

        Type type = new TypeToken<Mood[]>() {
        }.getType();

        arrayOfMood = gson.fromJson(json, type);

        if (arrayOfMood == null) {
            arrayOfMood = new Mood[7];
        }
        return arrayOfMood;
    }
}

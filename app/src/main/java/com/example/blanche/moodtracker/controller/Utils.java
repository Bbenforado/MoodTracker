package com.example.blanche.moodtracker.controller;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.blanche.moodtracker.models.Mood;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import static com.example.blanche.moodtracker.controller.MainActivity.APP_PREFERENCES;

public class Utils {

    private static final String KEY_ARRAY_MOOD = "arrayOfMood";

    /**
     * Retrieve the array of mood that is saved in preferences
     * Update the array:
     * Move the elements of the array to make sure that we always keep the newest moods saved
     * Save the updated array in the preferences
     * @param context
     * @param currentMood , the new mood to add to the array
     */
    public static void addMood(Context context, Mood currentMood) {
        Mood arrayOfMood[] = retrieveArrayOfMood(context);
        //move the elements
        for(int i = 0; i < arrayOfMood.length-1; i++) {
            arrayOfMood[i] = arrayOfMood[i+1];
        }
        arrayOfMood[6] = currentMood;
        //use gson to save the updated array in the preferences
        Gson gson = new Gson();
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ARRAY_MOOD, gson.toJson(arrayOfMood)).apply();
    }

    /**
     * Retrieve the array of the 7 last days saved moods.
     * @param context
     * @return an array of mood that can be updated
     */
    public static Mood[] retrieveArrayOfMood(Context context) {
        Mood[] arrayOfMood;
        Gson gson = new Gson();

        SharedPreferences preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String json = preferences.getString(KEY_ARRAY_MOOD, null);

        Type type = new TypeToken<Mood[]>() {
        }.getType();

        arrayOfMood = gson.fromJson(json, type);
        //if the array of mood isn't initialized, we initialized it
        if (arrayOfMood == null) {
            arrayOfMood = new Mood[7];
        }
        return arrayOfMood;
    }
}

package com.example.blanche.moodtracker.controller;


import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.blanche.moodtracker.R;
import com.example.blanche.moodtracker.models.Mood;

import static com.example.blanche.moodtracker.controller.Utils.retrieveArrayOfMood;


public class HistoricActivity extends AppCompatActivity implements View.OnClickListener {


    private String[] textForTextView = {"Il y'a 7 jours",
            "Il y'a 6 jours",
            "Il y'a 5 jours",
            "Il y'a 4 jours",
            "Il y'a 3 jours",
            "Avant-hier",
            "Hier"
    };
    private Mood[] arrayHistoric = new Mood[7];
    ViewGroup.LayoutParams layoutParams;
    private Button commentButton;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private int width;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
        System.out.println("ON CREATE HISTORIC");

        this.displayHistoric();
        this.linkTheWidgets();
        this.setTheOnClickListener();
    }

    /**
     * Display the historic views depending on each mood saved, and if there is a comment saved or not
     * Super happy mood = yellow background and full size width
     * happy mood = green background and 4/5 of width
     * normal mood = blue background and 3/5 of width
     * disappointed mood = grey background and 2/5 of width
     * sad mood = red background and 1/5 of width
     */
    private void displayHistoric() {
        arrayHistoric = retrieveArrayOfMood(this);

        for (int i = 0; i < arrayHistoric.length; i++) {
            int textViewId = getResources().getIdentifier("text" +(i+1), "id", getPackageName());
            text = findViewById(textViewId);
            int layoutId = getResources().getIdentifier("relativeLayout"+(i+1), "id", getPackageName());
            RelativeLayout relativeLayout = findViewById(layoutId);
            int buttonId = getResources().getIdentifier("button" +(i+1), "id", getPackageName());
            commentButton = findViewById(buttonId);

            if (arrayHistoric[i] != null) {
                int mood = arrayHistoric[i].getMoodTag();
                String comment = arrayHistoric[i].getComment();
                setButtonVisibility(comment);
                width = this.getWidth();
                text.setText(textForTextView[i]);

                switch (mood) {
                    case 0:
                        setParamsForNoMood(relativeLayout);
                        break;
                    case 1:
                        setParamsForSuperHappyMood(relativeLayout);
                        break;
                    case 2:
                        setParamsForHappyMood(relativeLayout);
                        break;
                    case 3:
                        setParamsForNormalMood(relativeLayout);
                        break;
                    case 4:
                       setParamsForDisappointedMood(relativeLayout);
                        break;
                    case 5:
                        setParamsForSadMood(relativeLayout);
                }
            } else {
                setParamsForNoHistoric(relativeLayout);
            }
        }
    }

    private void setButtonVisibility(String comment) {
        if (comment == null) {
            commentButton.setVisibility(View.INVISIBLE);
        } else {
            commentButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Set the background color and the width for each RelativeLayout, depending on the mood saved
     * @param relativeLayout TextView+Comment Button, which represents a mood
     */

    private void setParamsForSuperHappyMood(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
    }

    private void setParamsForHappyMood(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.light_sage));
        layoutParams = relativeLayout.getLayoutParams();
        layoutParams.width = 4 * width / 5;
        relativeLayout.setLayoutParams(layoutParams);
    }

    private void setParamsForNormalMood(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
        layoutParams = relativeLayout.getLayoutParams();
        layoutParams.width = 3 * width / 5;
        relativeLayout.setLayoutParams(layoutParams);
    }

    private void setParamsForDisappointedMood(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.warm_grey));
        layoutParams = relativeLayout.getLayoutParams();
        layoutParams.width = 2 * width / 5;
        relativeLayout.setLayoutParams(layoutParams);
    }

    private void setParamsForSadMood(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.faded_red));
        layoutParams = relativeLayout.getLayoutParams();
        layoutParams.width = width / 5;
        relativeLayout.setLayoutParams(layoutParams);
    }

    private void setParamsForNoHistoric(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.pink));
        text.setText(R.string.noHistoric);
        commentButton.setVisibility(View.INVISIBLE);
    }

    private void setParamsForNoMood(RelativeLayout relativeLayout) {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.pink));
        text.setText(R.string.noMood);
    }

    /**
     *
     * @return the width of the window
     */
    private int getWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        return width;
    }

    /**
     * Display the comment of the mood in toast message
     * @param v the comment button
     */
    @Override
    public void onClick(View v) {
        setCommentToButton();
        String comment = v.getTag().toString();
        Toast.makeText(this, comment, Toast.LENGTH_SHORT).show();
    }

    /**
     * Retrieve the comment of each mood and add it as tag to each comment button
     */
    private void setCommentToButton() {
        arrayHistoric = retrieveArrayOfMood(this);
        for (int i = 0; i < arrayHistoric.length; i++) {
            int buttonId = getResources().getIdentifier("button" +(i+1), "id", getPackageName());
            commentButton = findViewById(buttonId);
            String comment = arrayHistoric[i].getComment();
            commentButton.setTag(comment);
        }
    }

    private void linkTheWidgets() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
    }

    private void setTheOnClickListener() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
    }
}


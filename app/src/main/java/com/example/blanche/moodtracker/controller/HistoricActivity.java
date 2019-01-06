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



public class HistoricActivity extends AppCompatActivity implements View.OnClickListener {


    private String[] textForTextView = {"Il y'a 7 jours", "Il y'a 6 jours", "Il y'a 5 jours", "Il y'a 4 jours",
            "Il y'a 3 jours", "Avant-hier", "Hier"};
    private Mood[] arrayHistoric = new Mood[7];
    private Utils utils = new Utils();
    private TextView text;
    ViewGroup.LayoutParams layoutParams;
    private RelativeLayout relativeLayout;
    private int width;
    private Button commentButton;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);



        this.displayHistoric();
        this.linkTheWidgets();
        this.setTheOnClickListener();

        //quand un bouton est cliqué on disable es autres


    }

    //display historic
    private void displayHistoric() {

        arrayHistoric = utils.retrieveArrayOfMood(this);

        for (int i = 0; i < arrayHistoric.length; i++) {
            int textViewId = getResources().getIdentifier("text" +(i+1), "id", getPackageName());
            text = findViewById(textViewId);
            int layoutId = getResources().getIdentifier("relativeLayout"+(i+1), "id", getPackageName());
            relativeLayout = findViewById(layoutId);
            int buttonId = getResources().getIdentifier("button" +(i+1), "id", getPackageName());
            commentButton = findViewById(buttonId);


            if (arrayHistoric[i] != null) {
                int mood = arrayHistoric[i].getMoodTag();
                String comment = arrayHistoric[i].getComment();
                if (comment == null) {
                    //on n'affiche pas le bouton commentaire
                    commentButton.setVisibility(View.INVISIBLE);
                } else {
                    //on affiche le bouton commentaire
                    commentButton.setVisibility(View.VISIBLE);
                }
                width = this.getWidth();
                text.setText(textForTextView[i]);

                switch (mood) {
                    case 0:
                        relativeLayout.setBackgroundColor(getResources().getColor(R.color.pink));
                        text.setText(R.string.noMood);
                        break;
                    case 1:
                        relativeLayout.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
                        break;
                    case 2:
                        relativeLayout.setBackgroundColor(getResources().getColor(R.color.light_sage));
                        layoutParams = relativeLayout.getLayoutParams();
                        layoutParams.width = 4 * width / 5;
                        relativeLayout.setLayoutParams(layoutParams);
                        break;
                    case 3:
                        relativeLayout.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
                        layoutParams = relativeLayout.getLayoutParams();
                        layoutParams.width = 3 * width / 5;
                        relativeLayout.setLayoutParams(layoutParams);
                        break;
                    case 4:
                        relativeLayout.setBackgroundColor(getResources().getColor(R.color.warm_grey));
                        layoutParams = relativeLayout.getLayoutParams();
                        layoutParams.width = 2 * width / 5;
                        relativeLayout.setLayoutParams(layoutParams);
                        break;
                    case 5:
                        relativeLayout.setBackgroundColor(getResources().getColor(R.color.faded_red));
                        layoutParams = relativeLayout.getLayoutParams();
                        layoutParams.width = width / 5;
                        relativeLayout.setLayoutParams(layoutParams);
                }
            } else {
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.pink));
                text.setText(R.string.noHistoric);
                commentButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    //get the width
    private int getWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        return width;
    }

    @Override
    public void onClick(View v) {
        setCommentToButton();
        String comment = v.getTag().toString();
        Toast.makeText(this, comment, Toast.LENGTH_SHORT).show();

    }

    private void setCommentToButton() {
        arrayHistoric = utils.retrieveArrayOfMood(this);
        for (int i = 0; i < arrayHistoric.length; i++) {
            int buttonId = getResources().getIdentifier("button" +(i+1), "id", getPackageName());
            commentButton = findViewById(buttonId);
            String comment = arrayHistoric[i].getComment();
            commentButton.setTag(comment);
        }
    }

    //link the widgets
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


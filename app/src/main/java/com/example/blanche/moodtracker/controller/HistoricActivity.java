package com.example.blanche.moodtracker.controller;


import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.blanche.moodtracker.R;
import com.example.blanche.moodtracker.models.Mood;



public class HistoricActivity extends AppCompatActivity {


    private String[] textForTextView = {"Il y'a 7 jours", "Il y'a 6 jours", "Il y'a 5 jours", "Il y'a 4 jours",
            "Il y'a 3 jours", "Avant-hier", "Hier"};

    private Mood[] arrayHistoric = new Mood[7];

    private Utils utils = new Utils();

    private TextView text;

    ViewGroup.LayoutParams layoutParams;




    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        this.displayHistoric();


    }

    //display historic
    private void displayHistoric() {

        arrayHistoric = utils.retrieveArrayOfMood(this);

        for (int i = 0; i < arrayHistoric.length; i++) {

            text = findViewById(R.id.text + (i + 1));

            if (arrayHistoric[i] != null) {

                int mood = arrayHistoric[i].getMoodTag();

                String comment = arrayHistoric[i].getComment();

                if (comment == null) {
                    //on n'affiche pas le bouton commentaire
                } else {
                    //on affiche le bouton commentaire
                }

                width = this.getWidth();

                text.setText(textForTextView[i]);

                switch (mood) {
                    case 0:
                        text.setBackgroundColor(getResources().getColor(R.color.pink));
                        text.setText("You didn't save your mood that day! :(");
                        break;
                    case 1:
                        text.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
                        break;
                    case 2:
                        text.setBackgroundColor(getResources().getColor(R.color.light_sage));
                        layoutParams = text.getLayoutParams();
                        layoutParams.width = 4 * width / 5;
                        text.setLayoutParams(layoutParams);
                        break;
                    case 3:
                        text.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
                        layoutParams = text.getLayoutParams();
                        layoutParams.width = 3 * width / 5;
                        text.setLayoutParams(layoutParams);
                        break;
                    case 4:
                        text.setBackgroundColor(getResources().getColor(R.color.warm_grey));
                        layoutParams = text.getLayoutParams();
                        layoutParams.width = 2 * width / 5;
                        text.setLayoutParams(layoutParams);
                        break;
                    case 5:
                        text.setBackgroundColor(getResources().getColor(R.color.faded_red));
                        layoutParams = text.getLayoutParams();
                        layoutParams.width = width / 5;
                        text.setLayoutParams(layoutParams);
                }
            } else {
                text.setBackgroundColor(getResources().getColor(R.color.pink));
                text.setText("No historic reccorded yet :(\n Go save your mood of the day nd come back later :)");
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

}


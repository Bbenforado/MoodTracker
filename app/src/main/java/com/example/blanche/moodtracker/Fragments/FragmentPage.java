package com.example.blanche.moodtracker.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.blanche.moodtracker.R;
import com.example.blanche.moodtracker.controller.HistoricActivity;
import com.example.blanche.moodtracker.controller.Utils;

import static com.example.blanche.moodtracker.controller.MainActivity.APP_PREFERENCES;



/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPage extends Fragment {



    //create keys for the bundle
    private static final String KEY_POSITION = "position";
    private static final String KEY_COLOR = "color";
    private static final String KEY_IMAGE = "image";
    public static final String KEY_COMMENT = "comment";


    public FragmentPage() {
        // Required empty public constructor
    }

    public static FragmentPage newInstance(int position, int color, int image) {
        //create new fragment
        FragmentPage fragment = new FragmentPage();

        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);
        args.putInt(KEY_IMAGE, image);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_fragment_page, container, false);


        //on recupère les vues dont on a besoin
        RelativeLayout relativeLayout = result.findViewById(R.id.slideRelativeLayout);
        ImageView imageView = result.findViewById(R.id.slideImage);

        //on récupère les données du bundle
        int position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);
        int image = getArguments().getInt(KEY_IMAGE, -1);


        initButtons(result);

        relativeLayout.setBackgroundColor(color);
        imageView.setImageResource(image);

        return result;
    }


    //method that implements onClick methods for the buttons
    private void initButtons(View v) {
        Button historicButton = v.findViewById(R.id.historicButton);
        historicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display historic activity
                Intent historicActivity = new Intent(v.getContext(), HistoricActivity.class);
                startActivity(historicActivity);
            }
        });

        Button commentButton = v.findViewById(R.id.commentButton);
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                 final EditText editText = new EditText(v.getContext());
                 builder.setMessage("Commentaire:")
                       .setView(editText)
                     .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                       @Override
                     public void onClick(DialogInterface dialog, int which) {
                           //save the comment in the preferences
                           SharedPreferences preferences = getContext().getSharedPreferences(APP_PREFERENCES, 0);
                           preferences.edit().putString(KEY_COMMENT, editText.getText().toString()).apply();
                   }
                 })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                   @Override
                 public void onClick(DialogInterface dialog, int which) {
                   dialog.cancel();
                 }
                 })
                 .create()
                 .show();
            }
        });
    }
}

package com.example.blanche.moodtracker.controller.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.blanche.moodtracker.R;
import com.example.blanche.moodtracker.controller.Activities.HistoricActivity;


import static com.example.blanche.moodtracker.controller.Activities.MainActivity.APP_PREFERENCES;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPage extends Fragment {

    //create keys for the bundle
    private static final String KEY_POSITION = "position";
    private static final String KEY_COLOR = "color";
    private static final String KEY_IMAGE = "image";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_TEXT = "text";

    public FragmentPage() {
        // Required empty public constructor
    }

    public static FragmentPage newInstance(int position, int color, int image, String text) {
        //create new fragment
        FragmentPage fragment = new FragmentPage();

        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);
        args.putInt(KEY_IMAGE, image);
        args.putString(KEY_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_fragment_page, container, false);

        //retrieve views that are needed
        RelativeLayout relativeLayout = result.findViewById(R.id.slideRelativeLayout);
        ImageView imageView = result.findViewById(R.id.slideImage);
        Button shareButton = result.findViewById(R.id.buttonShare);

        //retrieve bundle data
        int position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);
        int image = getArguments().getInt(KEY_IMAGE, -1);
        String text = getArguments().getString(KEY_TEXT, null);
        initButtons(result);

        relativeLayout.setBackgroundColor(color);
        imageView.setImageResource(image);
        shareButton.setTag(text);

        return result;
    }

    /**
     * Retrieve the views we need (two buttons) and link the widgets with the id
     * Implements the OnClick() methods for each button
     * Historic button = launch the historic activity that display the 7 last days' moods
     * Comment button = display Alertdialog to allow the user to save one comment
     * @param v , the view displayed
     */
    private void initButtons(View v) {
        initHistoricButton(v);
        initCommentButton(v);
        initShareButton(v);
    }

    private void initShareButton(View v) {
        Button shareButton = v.findViewById(R.id.buttonShare);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt(v);
            }
        });
    }

    private void initCommentButton(View v) {
        Button commentButton = v.findViewById(R.id.commentButton);
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlertDialog(v);
            }
        });
    }

    private void displayAlertDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        final EditText editText = new EditText(v.getContext());
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
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
                });
        AlertDialog alertDialog = builder.create();
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    alertDialog.getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
        alertDialog.show();
    }

    private void initHistoricButton(View v) {
        Button historicButton = v.findViewById(R.id.historicButton);
        historicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display historic activity
                Intent historicActivity = new Intent(v.getContext(), HistoricActivity.class);
                startActivity(historicActivity);
            }
        });
    }

    private void shareIt(View v) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        //retrieve the comment
        SharedPreferences preferences = getContext().getSharedPreferences(APP_PREFERENCES, 0);
        String comment = preferences.getString(KEY_COMMENT, null);
        String shareBody;
        //if there's a comment display it
        if(comment != null) {
            shareBody = v.getTag() + "\n\nCommentaire:\n" + comment + "\n\n-Partagé avec MoodTracker-";
        } else {
            shareBody = v.getTag() +"\n\n-Partagé avec MoodTracker-" ;
        }
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.subject);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "partager via"));
    }


}

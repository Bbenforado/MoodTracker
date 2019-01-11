package com.example.blanche.moodtracker.models;

/**
 * Mood class is defined with one int (between 1 and 5) that represents the mood
 * 1 = Super happy
 * 2 = happy
 * 3 = normal
 * 4 = disappointed
 * 5 = sad
 * and one String that represent the comment left by the user, which is optional
 */
public class Mood {
    private String comment;
    private int moodTag;

    public Mood(String comment, int moodTag) {
        this.comment = comment;
        this.moodTag = moodTag;
    }

    //getters

    public int getMoodTag() {
        return moodTag;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Mood{" +
                "comment='" + comment + '\'' +
                ", moodTag=" + moodTag + '\'' +
                '}';
    }
}

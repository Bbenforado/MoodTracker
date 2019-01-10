package com.example.blanche.moodtracker.models;


import java.text.DateFormat;
import java.time.LocalDate;

public class Mood {


    //la classe mood représente l'humeur du jour

   // private Date updateAt;
    private String comment;
    private int moodTag;//int between 1 and 5
    private LocalDate date;

    public Mood(String comment, int moodTag, LocalDate date) {
        //this.updateAt = date;
        this.comment = comment;
        this.moodTag = moodTag;
        this.date = date;
    }

    public Mood(String comment, int moodTag) {
        this.comment = comment;
        this.moodTag = moodTag;
    }

    //setters & getters

    public LocalDate getDate() {
        return date;
    }

    public int getMoodTag() {
        return moodTag;
    }

    public void setMoodTag(int moodTag) {
        this.moodTag = moodTag;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Mood{" +
                "comment='" + comment + '\'' +
                ", moodTag=" + moodTag +
                '}';
    }
}

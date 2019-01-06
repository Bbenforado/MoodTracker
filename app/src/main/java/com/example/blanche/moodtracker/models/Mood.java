package com.example.blanche.moodtracker.models;


public class Mood {


    //la classe mood représente l'humeur du jour

   // private Date updateAt;
    private String comment;
    private int moodTag;//int between 1 and 5

    public Mood(String comment, int moodTag) {
        //this.updateAt = date;
        this.comment = comment;
        this.moodTag = moodTag;
    }

    public Mood(int moodTag) {
        this.moodTag = moodTag;
    }

    //setters & getters


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

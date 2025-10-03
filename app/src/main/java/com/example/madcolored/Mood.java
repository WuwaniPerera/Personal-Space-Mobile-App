package com.example.madcolored;

public class Mood {

    String id;
    private String date;
    private String mood;

    public Mood(){}

    public Mood(String id, String date, String mood) {
        this.id = id;
        this.date = date;
        this.mood = mood;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getMood() {
        return mood;
    }
}

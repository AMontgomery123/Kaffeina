package com.example.kaffeina;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Review {
    public String title;
    public int rating;
    public String body;
    public String id;
    public String uid;

    public Review(){

    }

    public Review(String title, int rating, String body, String id, String uid){
        this.title = title;
        this.rating = rating;
        this.body = body;
        this.id = id;
        this.uid = uid;
    }

}

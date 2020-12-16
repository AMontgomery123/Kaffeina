package com.example.kaffeina;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Review {
    //the review is consisted of a title, body, and rating
    //the review itself is tied to an ID and cafe ID
    public String title;
    public Float rating;
    public String body;
    public String uid;
    public String restaurant_id;

    public Review(){

    }

    public Review(String title, Float rating, String body, String uid, String restaurant_id){
        //constructor, set this.title = title and so on
        this.title = title;
        this.rating = rating;
        this.body = body;
        this.uid = uid;
        this.restaurant_id = restaurant_id;
    }

}

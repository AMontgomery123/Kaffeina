package com.example.kaffeina;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String uid;
    public String userName;
    public String userEmail;
    public int reviewCount = 0;
    public User(){

    }

    public User(String uid, String userName, String userEmail, int reviewCount){
        this.uid = uid;
        this.userName = userName;
        this.userEmail = userEmail;
        this.reviewCount = reviewCount;
    }
    public void increment(){
        this.reviewCount++;
    }
    public void setUser(User newUser) {
        this.uid = newUser.uid;
        this.userName = newUser.userName;
        this.userEmail = newUser.userEmail;
        this.reviewCount = newUser.reviewCount;
    }
}

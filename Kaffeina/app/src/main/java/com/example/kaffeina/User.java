package com.example.kaffeina;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    //user has an id, username, email, and a count of how many reviews they have done
    public String uid;
    public String userName;
    public String userEmail;
    public int reviewCount = 0;
    public User(){

    }

    public User(String uid, String userName, String userEmail, int reviewCount){
        //constructur
        this.uid = uid;
        this.userName = userName;
        this.userEmail = userEmail;
        this.reviewCount = reviewCount;
    }
    public void increment(){
        //increment the amount of reviews by 1
        this.reviewCount++;
    }
    public void setUser(User newUser) {
        //set the user as a new user
        this.uid = newUser.uid;
        this.userName = newUser.userName;
        this.userEmail = newUser.userEmail;
        this.reviewCount = newUser.reviewCount;
    }

    public String getString(){
        //return the user's information as a string
        String returnString = this.uid + " " + this.userName + " " + this.userEmail + " " + Integer.toString(this.reviewCount);
        return returnString;
    }
}

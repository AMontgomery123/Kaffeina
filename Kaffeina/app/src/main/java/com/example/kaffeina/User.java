package com.example.kaffeina;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String uid;
    public String name;
    public String email;

    public User(){

    }

    public User(String uid, String name, String email){
        this.uid = uid;
        this.name = name;
        this.email = email;
    }
}

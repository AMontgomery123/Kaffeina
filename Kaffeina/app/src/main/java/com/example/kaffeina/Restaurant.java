package com.example.kaffeina;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Restaurant {
    public String restaurant_address;
    public String restaurant_id;
    public String restaurant_name;

    public Restaurant(){

    }

    public Restaurant(String restaurant_address, String restaurant_id, String restaurant_name){
        this.restaurant_address = restaurant_address;
        this.restaurant_id = restaurant_id;
        this.restaurant_name = restaurant_name;
    }

}

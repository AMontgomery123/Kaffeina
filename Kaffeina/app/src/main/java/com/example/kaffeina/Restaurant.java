package com.example.kaffeina;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@SuppressWarnings("serial")
@IgnoreExtraProperties
public class Restaurant implements Serializable {
    public String restaurant_address;
    public String restaurant_id;
    public String restaurant_name;

    public String getRestaurant_Address(){
        return this.restaurant_address;
    }

    public void setRestaurantAddress(String address){
        this.restaurant_address = address;
    }

    public String getRestaurantId(){
        return this.restaurant_id;
    }

    public void setRestaurantID(String id){
        this.restaurant_id = id;
    }

    public String getRestaurantName(){
        return this.restaurant_name;
    }

    public void setRestaurantName(String name){
        this.restaurant_name = name;
    }

    public Restaurant(){

    }

    public Restaurant(String restaurant_address, String restaurant_id, String restaurant_name){
        this.restaurant_address = restaurant_address;
        this.restaurant_id = restaurant_id;
        this.restaurant_name = restaurant_name;
    }

}

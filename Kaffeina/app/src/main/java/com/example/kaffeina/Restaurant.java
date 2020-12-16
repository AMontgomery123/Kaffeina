package com.example.kaffeina;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


//this is a data class for the individual restaurants

@SuppressWarnings("serial")
@IgnoreExtraProperties
public class Restaurant implements Serializable {
    //create restaurant address, id, name, and list
    public String restaurant_address;
    public String restaurant_id;
    public String restaurant_name;
    public String beverage_list;

    public String getrestaurant_address(){
        return this.restaurant_address;
    }

    public void setrestaurant_address(String address){
        //set up the restaurant address
        this.restaurant_address = address;
    }

    public String getrestaurant_id(){
        //return the restaurant id
        return this.restaurant_id;
    }

    public void setrestaurant_id(String id){
        //set the restaurant id
        this.restaurant_id = id;
    }

    public String getrestaurant_name(){
        //return the restaurant name
        return this.restaurant_name;
    }

    public void setrestaurant_name(String name){
        //set the restaurant name
        this.restaurant_name = name;
    }

    public String getbeverage_list(){return beverage_list;}

    public void setbeverage_list(String blist){
        //set the beverage list
        this.beverage_list = blist;
    }

    public Restaurant(){
    }

    public Restaurant(String restaurant_address, String restaurant_id, String restaurant_name, String beverage_list){
        this.restaurant_address = restaurant_address;
        this.restaurant_id = restaurant_id;
        this.restaurant_name = restaurant_name;
        this.beverage_list = beverage_list;
    }

    public Restaurant(Restaurant instRest){
        this.beverage_list = instRest.beverage_list;
        this.restaurant_id = instRest.restaurant_id;
        this.restaurant_name = instRest.restaurant_name;
        this.restaurant_address = instRest.restaurant_address;
    }

    public void setRestaurant(Restaurant newRestaurant){
        this.beverage_list = newRestaurant.beverage_list;
        this.restaurant_id = newRestaurant.restaurant_id;
        this.restaurant_name = newRestaurant.restaurant_name;
        this.restaurant_address = newRestaurant.restaurant_address;
    }

}

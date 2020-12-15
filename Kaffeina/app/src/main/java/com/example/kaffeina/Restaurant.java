package com.example.kaffeina;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@IgnoreExtraProperties
public class Restaurant implements Serializable {
    public String restaurant_address;
    public String restaurant_id;
    public String restaurant_name;
    public List<String> beverage_list;

    public String getrestaurant_address(){
        return this.restaurant_address;
    }

    public void setrestaurant_address(String address){
        this.restaurant_address = address;
    }

    public String getrestaurant_id(){
        return this.restaurant_id;
    }

    public void setrestaurant_id(String id){
        this.restaurant_id = id;
    }

    public String getrestaurant_name(){
        return this.restaurant_name;
    }

    public void setrestaurant_name(String name){
        this.restaurant_name = name;
    }

    public List<String> getbeverage_list(){return beverage_list;}

    public void setbeverage_list(List<String> blist){
        this.beverage_list.clear();
        this.beverage_list.addAll(blist);
    }

    public Restaurant(){

    }

    public Restaurant(String restaurant_address, String restaurant_id, String restaurant_name, List<String> beverage_list){
        this.restaurant_address = restaurant_address;
        this.restaurant_id = restaurant_id;
        this.restaurant_name = restaurant_name;
        this.beverage_list = beverage_list;
    }

    public void setRestaurant(Restaurant newRestaurant){
        if(newRestaurant.beverage_list != null) {
            this.beverage_list.clear();
        }
        this.beverage_list.addAll(newRestaurant.beverage_list);
        this.restaurant_id = newRestaurant.restaurant_id;
        this.restaurant_name = newRestaurant.restaurant_name;
        this.restaurant_address = newRestaurant.restaurant_address;
    }

}

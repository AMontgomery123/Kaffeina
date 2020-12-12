package com.example.kaffeina;

public class BeverageProfile {
    public String name = "";
    public int calories = 0;
    public String profile = "";
    public String beverageID = "";
    public void getName(){
         System.out.println(name);
    }
    public int getCalories(){
        return calories;
    }
    public void getReview(){
        System.out.println(profile);
    }
    public BeverageProfile(){

    }
    public BeverageProfile(String name, int calories, String profile, String uID, String beverageID){
        this.name = name;
        this.calories = calories;
        this.profile = profile;
        this.beverageID = beverageID;

    }
    public void  setBeverageProfile(BeverageProfile newBeverage){
        this.name = newBeverage.name;
        this.calories = newBeverage.calories;
        this.profile = newBeverage.profile;
        this.beverageID = newBeverage.beverageID;
    }
}

package com.example.kaffeina;

public class BeverageProfile {
    private String name = "";
    private int calories = 0;
    private String profile = "";
    public String beverageID = "";
    private void getName(){
         System.out.println(name);
    }
    private int getCalories(){
        return calories;
    }
    private void getReview(){
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
}

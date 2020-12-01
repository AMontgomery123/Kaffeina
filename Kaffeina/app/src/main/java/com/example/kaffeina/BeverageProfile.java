package com.example.kaffeina;

public class BeverageProfile {
    private String name = "";
    private int calories = 0;
    private String[] allergicIngredients;
    private String review = "";
    private void getName(){
         System.out.println(name);
    }
    private int getCalories(){
        return calories;
    }
    private void getAllergicIngredients(){
        System.out.println(allergicIngredients);
    }
    private void getReview(){
        System.out.println(review);
    }

}

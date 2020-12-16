package com.example.kaffeina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class search_query extends AppCompatActivity{
    TextView restaurant_title, restaurant_address, restaurant_profile;
    Button log_in, add_beverage;
    FirebaseDatabase database;
    private FirebaseUser current_user;

    //Create a new restaurant
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_query);


    }
}

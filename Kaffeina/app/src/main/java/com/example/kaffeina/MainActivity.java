package com.example.kaffeina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button registerTest, loginTest, reviewTests, createBeverageTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerTest = findViewById(R.id.registerTestButton);
        loginTest = findViewById(R.id.loginTestButton);
        reviewTests = findViewById(R.id.reviewTestButton);
        createBeverageTest = findViewById(R.id.AddBeverage);
        registerTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

        loginTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
        reviewTests.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, CreateReview.class));
            }
        });
        createBeverageTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(MainActivity.this, AddBeverage.class);
                //Suds will make a restaurant class for the restaurant that will be clicked and have the
                //id be passed from that parameter class
                myIntent.putExtra("Restaurant ID", "restaurantUniqueID");
            startActivity(myIntent);
        };

    });
}}
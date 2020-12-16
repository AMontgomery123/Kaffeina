package com.example.kaffeina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //set up the buttons for the various tests
    Button registerTest, loginTest, reviewTests, createBeverageTest, searchTest, restaurantTest;
    FirebaseUser current_user;
    FirebaseAuth authMonster;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    //Initialize all buttons from the XML to variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //have the tests be able to found by Id
        registerTest = findViewById(R.id.registerTestButton);
        loginTest = findViewById(R.id.loginTestButton);
        reviewTests = findViewById(R.id.reviewTestButton);
        createBeverageTest = findViewById(R.id.AddBeverage);
        searchTest = findViewById(R.id.Searchbutton);
        restaurantTest = findViewById(R.id.restaurantTestButton);

        //When clicking Register Test button, be directed to Register class.
        registerTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
        //When clicking Restaurant test button, be directed to Create Restaurant class.
        restaurantTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Restaurant testRest = new Restaurant("2233 Slippy Lane, San Jose, CA 94592", "44544", "Starbucks", "Coffee,Late,Juice,Milk Tea");
                Intent myIntent = new Intent(MainActivity.this, CreateRestaurant.class);
                //Suds will make a restaurant class for the restaurant that will be clicked and have the
                //id be passed from that parameter class
                myIntent.putExtra("restaurant", testRest);
                startActivity(myIntent);
            }
        });
        //When clicking Search test button, be directed to Searchable Activity class.
        searchTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, SearchableActivity.class));
            }
        });

        //When clicking Login test button, be directed to Login class.
        loginTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
        
        //When clicking Review test button, be directed to CreateReview class.
        reviewTests.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
//                mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//                    @Override
//                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                        current_user = authMonster.getCurrentUser();
//
//                    }
//                };

                if(1==0)//Auth.auth().currentUser)
                {
                    Toast.makeText(MainActivity.this, "please Log in", Toast.LENGTH_LONG).show();
                }
                else {
                    startActivity(new Intent(MainActivity.this, CreateReview.class));
                }
            }
        });
        //When clicking Create Beverage test button, be directed to AddBeverage class.
        createBeverageTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//                    @Override
//                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                        current_user = authMonster.getCurrentUser();
//
//                    }
//                };
               // current_user = FirebaseAuth.getInstance().getCurrentUser();

                if(1==0)//current_user == null)
                {
                    Toast.makeText(MainActivity.this, "please Log in", Toast.LENGTH_LONG).show();
                }
                else {
                    Restaurant testRest = new Restaurant("2233 Slippy Lane, San Jose, CA 94592", "44544", "Starbucks", "water");

                    Intent myIntent = new Intent(MainActivity.this, AddBeverage.class);
                    //Suds will make a restaurant class for the restaurant that will be clicked and have the
                    //id be passed from that parameter class
                    myIntent.putExtra("restaurant", testRest);
                    startActivity(myIntent);
                }
        };

    });
}}

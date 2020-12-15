package com.example.kaffeina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.kaffeina.Review;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

public class CreateRestaurant extends AppCompatActivity{
    TextView restaurant_title;
    EditText restaurant_profile;
    FirebaseDatabase database;
    private FirebaseUser current_restaurant;

    //Button for creating review
    Button checkMenu;
    User updateUser = new User();

    //Create a new restaurant
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);

        Intent intent = getIntent();
        Restaurant current_restaurant = (Restaurant) intent.getSerializableExtra("Restaurant ID");

        restaurant_title.findViewById(R.id.restaurantTitle);
        restaurant_profile.findViewById(R.id.restaurantProfile);

        restaurant_title.setText(current_restaurant.restaurant_name);

    }
}

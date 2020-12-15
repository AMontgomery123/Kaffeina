package com.example.kaffeina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;

public class CreateRestaurant extends AppCompatActivity{
    TextView restaurant_title;
    EditText restaurant_profile;
    ListView beverageListView;
    List beverageList = new ArrayList();
    ArrayAdapter adapter;
    FirebaseDatabase database;
    private FirebaseUser current_restaurant;

    //Button for creating review
    Button checkMenu;
    User updateUser = new User();

    //Create a new restaurant
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent intent = getIntent();
        Restaurant current_restaurant = (Restaurant) intent.getSerializableExtra("restaurant_pass");

        restaurant_title = findViewById(R.id.restaurantTitle);
        restaurant_profile = findViewById(R.id.restaurantProfile);

        restaurant_title.setText(current_restaurant.restaurant_name);

        beverageListView = (ListView)findViewById(R.id.beverage_list);

        beverageList.add("Black Coffee");
        beverageList.add("Carmel Machiato");
        beverageList.add("Green Tea");
        beverageList.add("Water");
        beverageList.add("Orange Juice");
        beverageList.add("yo mama");

        adapter = new ArrayAdapter(CreateRestaurant.this, android.R.layout.simple_list_item_1, beverageList);
        beverageListView.setAdapter(adapter);

    }
}

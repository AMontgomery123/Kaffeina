package com.example.kaffeina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.Arrays;
import java.util.List;

public class CreateRestaurant extends AppCompatActivity{
    // instantiate class variables
    TextView restaurant_title, restaurant_address, restaurant_profile;
    Button log_in, add_beverage;
    ListView beverageListView;
    List beverageList;
    ArrayAdapter adapter;
    FirebaseDatabase database;
    private FirebaseUser current_user;

    //Create a new restaurant
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // link to xml
        setContentView(R.layout.activity_restaurant);

        // get user and read their Id
        current_user = FirebaseAuth.getInstance().getCurrentUser();
        final String user_id = current_user.getUid();
        // Toast.makeText(CreateRestaurant.this, "User Id: "+user_id.toString(), Toast.LENGTH_LONG).show();

        // get data passed in by caller
        Intent intent = getIntent();
        final Restaurant current_restaurant = (Restaurant) intent.getSerializableExtra("restaurant");
        
        // create list of beverages
        if(current_restaurant.beverage_list.length() > 0) {
            String[] bev_list = current_restaurant.beverage_list.split(",");
            beverageList = new ArrayList<String>(Arrays.asList(bev_list));
        }
        else{
            beverageList = new ArrayList<String>();
            beverageList.add("no beverages");
        }

        restaurant_title = findViewById(R.id.restaurantTitle);
        restaurant_profile = findViewById(R.id.restaurantProfile);
        restaurant_address = findViewById(R.id.restaurantAddress);
        log_in = findViewById(R.id.logInButton);
        add_beverage = findViewById(R.id.addBeverageButton);

        // set text to fields supplied by caller
        restaurant_title.setText(current_restaurant.restaurant_name);
        restaurant_address.setText(current_restaurant.restaurant_address);

        // link to list view
        beverageListView = (ListView)findViewById(R.id.beverage_list);

        // adapter will associate list view with the list to be displayed
        adapter = new ArrayAdapter(CreateRestaurant.this, android.R.layout.simple_list_item_1, beverageList);
        beverageListView.setAdapter(adapter);

        // register user clicks on list items
        beverageListView.setClickable(true);
        beverageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = beverageListView.getItemAtPosition(position);
                if (o.toString() != "no beverages"){
                    // call beverage profile and pass it the necessary data
                    Intent next_intent = new Intent(CreateRestaurant.this, CreateBeverageProfile.class);
                    next_intent.putExtra("beverage_name", o.toString());
                    next_intent.putExtra("restaurant", current_restaurant);
                    startActivity(next_intent);
                }
                Toast.makeText(CreateRestaurant.this, "Clicked: "+o.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // send user to login
        log_in.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateRestaurant.this, Login.class));
            }
        });

        //send user to create beverage
        add_beverage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(user_id == null){
                    Toast.makeText(CreateRestaurant.this, "Please log in to create beverage", Toast.LENGTH_LONG).show();
                }
                else {
                    // pass necessary data to add beverage
                    Intent next_intent = new Intent(CreateRestaurant.this, AddBeverage.class);
                    next_intent.putExtra("restaurant", current_restaurant);
                    startActivity(next_intent);
                }
            }
        });

    }
}

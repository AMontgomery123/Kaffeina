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
import java.util.List;

public class CreateRestaurant extends AppCompatActivity{
    TextView restaurant_title, restaurant_address, restaurant_profile;
    Button log_in, add_beverage;
    ListView beverageListView;
    List beverageList = new ArrayList();
    ArrayAdapter adapter;
    FirebaseDatabase database;
    private FirebaseUser current_user;

    //Create a new restaurant
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        current_user = FirebaseAuth.getInstance().getCurrentUser();
        final String user_id = current_user.getUid();
        // Toast.makeText(CreateRestaurant.this, "User Id: "+user_id.toString(), Toast.LENGTH_LONG).show();

        Intent intent = getIntent();
        final Restaurant current_restaurant = (Restaurant) intent.getSerializableExtra("restaurant_pass");

        restaurant_title = findViewById(R.id.restaurantTitle);
        restaurant_profile = findViewById(R.id.restaurantProfile);
        restaurant_address = findViewById(R.id.restaurantAddress);
        log_in = findViewById(R.id.logInButton);
        add_beverage = findViewById(R.id.addBeverageButton);

        restaurant_title.setText(current_restaurant.restaurant_name);
        restaurant_address.setText(current_restaurant.restaurant_address);

        beverageListView = (ListView)findViewById(R.id.beverage_list);

        beverageList.add("Black Coffee");
        beverageList.add("Carmel Machiato");
        beverageList.add("Green Tea");
        beverageList.add("Water");
        beverageList.add("Orange Juice");
        beverageList.add("yo mama");

        adapter = new ArrayAdapter(CreateRestaurant.this, android.R.layout.simple_list_item_1, beverageList);
        beverageListView.setAdapter(adapter);

        beverageListView.setClickable(true);
        beverageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = beverageListView.getItemAtPosition(position);
                Toast.makeText(CreateRestaurant.this, "Clicked: "+o.toString(), Toast.LENGTH_LONG).show();
//                Intent next_intent = new Intent(CreateRestaurant.this, CreateBeverageProfile.class);
//                next_intent.putExtra("beverage_clicked", o.toString());
//                startActivity(next_intent);
            }
        });

        log_in.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateRestaurant.this, Login.class));
            }
        });

        add_beverage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(user_id == null){
                    Toast.makeText(CreateRestaurant.this, "Please log in to create beverage", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent next_intent = new Intent(CreateRestaurant.this, AddBeverage.class);
                    next_intent.putExtra("restaurant", current_restaurant);
                    startActivity(next_intent);
                }
            }
        });

    }
}

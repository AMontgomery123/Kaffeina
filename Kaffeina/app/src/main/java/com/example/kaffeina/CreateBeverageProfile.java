package com.example.kaffeina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CreateBeverageProfile extends AppCompatActivity {
    //Title
    TextView beverageProfile_title, beverageProfile_reviewAmount, beverageProfile_description;
    RatingBar beverageProfile_reviews;
    FirebaseDatabase database;
    private FirebaseUser current_user;
    DatabaseReference profile_ref, profile_by_user_ref;
    //Button
    Button createReview, logIn;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverageprofile);

        current_user = FirebaseAuth.getInstance().getCurrentUser();
        final String user_id = current_user.getUid();

        beverageProfile_title = findViewById(R.id.beverageTitle);
        beverageProfile_reviews = findViewById(R.id.beverageConsensus);
        beverageProfile_reviewAmount = findViewById(R.id.numberOfReviews);
        beverageProfile_description = findViewById(R.id.beverageDescription);
        createReview = findViewById(R.id.create_review);
        logIn = findViewById(R.id.logInBeverageButton);

        database = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        final Restaurant current_restaurant = (Restaurant) intent.getSerializableExtra("restaurant");
        final String beverage_name = intent.getStringExtra("beverage_name");

        beverageProfile_title.setText(beverage_name);

        createReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_id == null){
                    Toast.makeText(CreateBeverageProfile.this, "Please log in to create beverage", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent next_intent = new Intent(CreateBeverageProfile.this, CreateReview.class);
                    next_intent.putExtra("restaurant", current_restaurant);
                    next_intent.putExtra("beverage_name", beverage_name);
                    startActivity(next_intent);
                }
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateBeverageProfile.this, Login.class));
            }
        });



    }
}

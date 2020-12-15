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
    TextView beverageProfile_title;
    TextView beverageProfile_allergic_ingredients;
    TextView beverageProfile_calories;
    TextView beverageProfile_reviewAmount;
    RatingBar beverageProfile_reviews;
    TextView beverageProfile_description;
    FirebaseDatabase database;
    private FirebaseUser restaurantOwner;
    DatabaseReference profile_ref, profile_by_user_ref;
    EditText beverageProfile_body;
    //Button
    Button createBeverageProfile;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverageprofile);

        beverageProfile_title = findViewById(R.id.beverageTitle);
        beverageProfile_reviews = findViewById(R.id.beverageConsensus);
        beverageProfile_reviewAmount = findViewById(R.id.numberOfReviews);
        beverageProfile_description = findViewById(R.id.beverageDescription);



    }
}

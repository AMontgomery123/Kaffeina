package com.example.kaffeina;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Review extends AppCompatActivity {
    TextView review_title;
    RatingBar rating;
    EditText review_body;
    FirebaseDatabase database;
    DatabaseReference title_ref, body_ref, rating_ref;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        review_title = findViewById(R.id.reviewTitle);
        rating = findViewById(R.id.ratingBar);
        review_body = findViewById(R.id.reviewBody);
        database = FirebaseDatabase.getInstance();
        title_ref = database.getReference("review_title");
        rating_ref = database.getReference("review_rating");
        body_ref = database.getReference("review_body");




    }
}

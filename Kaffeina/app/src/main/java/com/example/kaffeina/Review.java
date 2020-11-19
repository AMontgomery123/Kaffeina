package com.example.kaffeina;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Review extends AppCompatActivity {
    //Title for review, rating, and body
    TextView review_title;
    RatingBar rating_field;
    EditText review_body;
    FirebaseDatabase database;
    DatabaseReference title_ref, body_ref, rating_ref;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    //Button for creating review
    Button createReview;

    //Creates a new review
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        review_title = findViewById(R.id.reviewTitle);
        rating_field = findViewById(R.id.ratingBar);
        review_body = findViewById(R.id.reviewBody);
        
        createReview = findViewById(R.id.create_review);
        createReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String title = review_title.getText().toString();
                int ratingScore = rating.getNumStars();
                String actualReview = review_body.getText().toString();
                database = FirebaseDatabase.getInstance();
                title_ref = database.getReference("review_title");
                rating_ref = database.getReference("review_rating");
                body_ref = database.getReference("review_body");
                title_ref.setValue(title);
                rating_ref.setValue(ratingScore);
                body_ref.setValue(actualReview);
            }
        });
    }
}

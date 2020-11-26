package com.example.kaffeina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.kaffeina.Review;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

public class CreateReview extends AppCompatActivity {
    //Title for review, rating, and body
    TextView review_title;
    RatingBar rating_field;
    EditText review_body;
    FirebaseDatabase database;
    DatabaseReference review_ref;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    //Button for creating review
    Button createReview;

    //Creates a new review
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);

        review_title = findViewById(R.id.reviewTitle);
        rating_field = findViewById(R.id.ratingBar);
        review_body = findViewById(R.id.reviewBody);

        createReview = findViewById(R.id.createReview);
        createReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String title = review_title.getText().toString();
                int ratingScore = -1;
                ratingScore = rating_field.getNumStars();
                String actualReview = review_body.getText().toString();

                if(title.isEmpty()){
                    review_title.setError("Please enter a title");
                    review_title.requestFocus();
                }
                else if(ratingScore == -1){
                    Toast.makeText(CreateReview.this, "please provide a rating score", Toast.LENGTH_LONG).show();
                    rating_field.requestFocus();
                }
                else if(actualReview.isEmpty()){
                    review_body.setError("Please enter a review");
                    review_body.requestFocus();
                }

                Review review = new Review(title, ratingScore, actualReview, "1", "2");

                database = FirebaseDatabase.getInstance();
                review_ref = database.getReference("Reviews");

                review_ref.setValue(review);
            }
        });
        //startActivity(new Intent(Review.this, MainActivity.class));
    }
}

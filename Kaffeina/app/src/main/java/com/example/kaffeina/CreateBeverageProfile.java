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
        setContentView(R.layout.activity_create_review);

        beverageProfile_title = findViewById(R.id.beverageTitle);
        beverageProfile_reviews = findViewById(R.id.beverageConsensus);
        beverageProfile_reviewAmount = findViewById(R.id.numberOfReviews);
        beverageProfile_description = findViewById(R.id.beverageDescription);
        createBeverageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantOwner = FirebaseAuth.getInstance().getCurrentUser();
                if (restaurantOwner == null) {
                    Toast.makeText(CreateBeverageProfile.this, "please provide a rating score", Toast.LENGTH_LONG).show();
                } else {
                    String user_id = restaurantOwner.getUid();
                    String title = beverageProfile_title.getText().toString();
                    int ratingScore = -1;
                    ratingScore = beverageProfile_reviews.getNumStars();
                    String description = beverageProfile_description.getText().toString();

                    if (title.isEmpty()) {
                        beverageProfile_title.setError("Please enter a title");
                        beverageProfile_title.requestFocus();
                    } else if (ratingScore == -1) {
                        Toast.makeText(CreateBeverageProfile.this, "please provide a rating score", Toast.LENGTH_LONG).show();
                        beverageProfile_reviews.requestFocus();
                    } else if (description.isEmpty()) {
                        beverageProfile_description.setError("Please submit a description of the beverage");
                        beverageProfile_description.requestFocus();
                    }
                    BeverageProfile bevProf = new BeverageProfile(title, ratingScore, description, user_id, "2");
                    final User updateUser = new User();
                    database = FirebaseDatabase.getInstance();
                    DatabaseReference mdata = database.getReference().child("Users/" + user_id);
                    mdata.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            User testUser = snapshot.getValue(User.class);
                            updateUser.setUser(testUser);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                    String unique_id = "00101111";
                    updateUser.reviewCount++;
                    profile_ref = database.getReference("Reviews/" + unique_id);
                    profile_by_user_ref = database.getReference("Review_by_user/" + user_id);
                    //we incremented the review count and pushed the database
                    mdata.setValue(updateUser);
                    profile_ref.setValue(bevProf);
                    profile_by_user_ref.setValue(unique_id);

                    startActivity(new Intent(CreateBeverageProfile.this, MainActivity.class));
                }
            }
        });
    }
}
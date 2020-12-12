//package com.example.kaffeina;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.example.kaffeina.Review;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.IgnoreExtraProperties;
//import com.google.firebase.database.ValueEventListener;
//
//public class CreateRestaurant extends AppCompatActivity{
//    TextView restaurant_title;
//    EditText restaurant_profile;
//    FirebaseDatabase database;
//    private FirebaseUser current_restaurant;
//
//    //Button for creating review
//    Button checkMenu;
//    User updateUser = new User();
//
//    //Create a new restaurant
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_review);
//
////        restaurant_title = findViewById(R.id.restaurantTitle);
////        restaurant_profile = findViewById(R.id.restaurantProfile);
////            @Override
////            public void onClick(View v) {
////                current_restaurant = FirebaseAuth.getInstance().getCurrentUser();
////                if( current_restaurant == null){
////                    Toast.makeText(CreateReview.this, "please provide a rating score", Toast.LENGTH_LONG).show();
////                }
////                else {
////                    String user_id = current_user.getUid();
////                    String title = review_title.getText().toString();
////                    int ratingScore = -1;
////                    ratingScore = rating_field.getNumStars();
////                    String actualReview = review_body.getText().toString();
////
////                    if (title.isEmpty()) {
////                        review_title.setError("Please enter a title");
////                        review_title.requestFocus();
////                    } else if (ratingScore == -1) {
////                        Toast.makeText(CreateReview.this, "please provide a rating score", Toast.LENGTH_LONG).show();
////                        rating_field.requestFocus();
////                    } else if (actualReview.isEmpty()) {
////                        review_body.setError("Please enter a review");
////                        review_body.requestFocus();
////                    }
////
////
////                    Review review = new Review(title, ratingScore, actualReview, user_id, "2");
////                    database = FirebaseDatabase.getInstance();
////
////                    String unique_id = user_id + "@" + Long.toString(System.currentTimeMillis());
////                    review_ref = database.getReference("Reviews/" + unique_id);
////                    review_by_user_ref = database.getReference("Review_by_user/" + user_id);
////
////                    review_ref.setValue(review);
////                    review_by_user_ref.setValue(unique_id);
////                    Toast.makeText(CreateReview.this, user_id, Toast.LENGTH_LONG).show();
//
//                    DatabaseReference mdata = database.getReference().child("User/" + user_id);
//                    mdata.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            User testUser = snapshot.getValue(User.class);
//                            updateUser.setUser(testUser);
//                        }
//
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//
//                    });
//                    DatabaseReference updateUserReviewCount = database.getReference("User/"+user_id+"/reviewCount");
//                    updateUser.reviewCount++;
//                    Toast.makeText(CreateReview.this, "updateUser: "+Integer.toString(updateUser.reviewCount), Toast.LENGTH_LONG).show();
//                    updateUserReviewCount.setValue(updateUser.reviewCount);
//
//                    startActivity(new Intent(CreateReview.this, MainActivity.class));
//                }
//            }
//        });
//    }
//    }
//}

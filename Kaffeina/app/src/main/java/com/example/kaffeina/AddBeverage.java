package com.example.kaffeina;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddBeverage extends AppCompatActivity {
    TextView beverage_name;
    TextView beverage_info;
    //Button for adding the beverage
    Button addBeverage;
    FirebaseDatabase database;
    DatabaseReference beverage_ref, beverage_by_user_ref;
    private FirebaseUser current_user;
    BeverageProfile bP = new BeverageProfile();
    //
    User updateUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);
        beverage_name = findViewById(R.id.restaurantTitle);
        addBeverage = findViewById(R.id.addBeverage);
        if( current_user == null){
            Toast.makeText(AddBeverage.this, "please add a beverage", Toast.LENGTH_LONG).show();
        }
        else {
            String user_id = current_user.getUid();
            String beverageName = beverage_name.getText().toString();
            int calories = -1;
            String beverageInfo = beverage_info.getText().toString();

            if (beverageName.isEmpty()) {
                beverage_name.setError("Please enter a title");
                beverage_name.requestFocus();
            } else if (beverageInfo.isEmpty()) {
                beverage_info.setError("Please enter a description");
                beverage_info.requestFocus();
            }


            BeverageProfile beverage = new BeverageProfile(user_id, calories, beverageInfo, user_id, "2");
            database = FirebaseDatabase.getInstance();

            String unique_id = user_id + "@" + Long.toString(System.currentTimeMillis());
            beverage_by_user_ref = database.getReference("Beverage_by_user/" + user_id);

            beverage_ref.setValue(beverage);
            beverage_by_user_ref.setValue(unique_id);
            Toast.makeText(AddBeverage.this, user_id, Toast.LENGTH_LONG).show();

            DatabaseReference mdata = database.getReference().child("User/" + user_id);
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
            DatabaseReference updateUserReviewCount = database.getReference("User/"+user_id+"/reviewCount");
            updateUser.reviewCount++;
            Toast.makeText(AddBeverage.this, "updateUser: "+Integer.toString(updateUser.reviewCount), Toast.LENGTH_LONG).show();
            updateUserReviewCount.setValue(updateUser.reviewCount);

            startActivity(new Intent(AddBeverage.this, MainActivity.class));
        }
    }
}

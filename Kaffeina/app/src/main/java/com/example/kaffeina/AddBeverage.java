package com.example.kaffeina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class AddBeverage extends AppCompatActivity {
    BeverageProfile checkBeverage = new BeverageProfile();
    TextView beverage_name;
    TextView beverage_info;
    //Button for adding the beverage
    Button addBeverage;
    FirebaseDatabase database;
    DatabaseReference beverage_ref, beverage_by_user_ref;
    private FirebaseUser current_user;
    BeverageProfile bP = new BeverageProfile();
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbeverage);
        beverage_name = findViewById(R.id.beverageName);
        beverage_info = findViewById(R.id.beverageInfo);
        addBeverage = findViewById(R.id.addBeverage);

        addBeverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_user = FirebaseAuth.getInstance().getCurrentUser();
                if (current_user == null) {
                    Toast.makeText(AddBeverage.this, "please sign in", Toast.LENGTH_LONG).show();
                } else {
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

                    Intent intent = getIntent();
                    String restaurant = intent.getStringExtra("Restaurant ID");

                    BeverageProfile beverage = new BeverageProfile(beverageName, calories, beverageInfo, user_id, "2");
                    database = FirebaseDatabase.getInstance();

                    String unique_beverage_id = restaurant + "@" + beverage.name;

                    DatabaseReference mdata = database.getReference().child("Beverage/" + unique_beverage_id);
                    mdata.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            BeverageProfile testBeverage = snapshot.getValue(BeverageProfile.class);
                            checkBeverage.setBeverageProfile(testBeverage);
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                    //checks if the beverages are already added or not
                    if (checkBeverage.name == null) {
                        beverage_by_user_ref = database.getReference("Beverage_by_user/" + user_id);
                        beverage_ref = database.getReference("Beverage/" + unique_beverage_id);
                        beverage_ref.setValue(beverage);
                        beverage_by_user_ref.setValue(unique_beverage_id);
                    }
                    //if they already exist, it'll give an error message
                    else {
                        Toast.makeText(AddBeverage.this, "ERROR: Beverage Already Exist: "+checkBeverage.name, Toast.LENGTH_LONG).show();
                    }


                    startActivity(new Intent(AddBeverage.this, MainActivity.class));
                }
            }
        });
    }
}

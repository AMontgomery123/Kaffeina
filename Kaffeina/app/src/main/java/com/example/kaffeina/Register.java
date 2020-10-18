package com.example.kaffeina;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;


import android.os.Bundle;

public class Register extends AppCompatActivity {
   TextView nameField;
   EditText emailField, passwordField;
   FirebaseAuth Gandalph;
   Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Gandalph = FirebaseAuth.getInstance();
        emailField = findViewById(R.id.emailAddress);
        passwordField = findViewById(R.id.password);
        nameField = findViewById(R.id.fullName);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                String name = nameField.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    //tell user to go fuck themselves
                }
            }
        });
    }
}
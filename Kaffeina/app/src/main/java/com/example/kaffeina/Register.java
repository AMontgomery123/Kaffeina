package com.example.kaffeina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import android.os.Bundle;
import android.widget.Toast;

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

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                String name = nameField.getText().toString();
                boolean missingFields = false;

                if (name.isEmpty()) {
                    nameField.setError("Please enter a name");
                    nameField.requestFocus();
                    missingFields = true;
                }
                else if (email.isEmpty()) {
                    emailField.setError("Please enter an email address");
                    emailField.requestFocus();
                    missingFields = true;
                } else if (password.isEmpty()) {
                    passwordField.setError("Please enter a password");
                    passwordField.requestFocus();
                    missingFields = true;
                }

                if (missingFields) {
                    Toast.makeText(Register.this, "Please fill in all fields and re-submit", Toast.LENGTH_SHORT).show();
                } else {
                    Gandalph.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Register.this, "SignUp unsuccessful.  Did you sell your soul to Milhouse again?", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(Register.this, MainActivity.class));
                            }
                        }
                    });
                }
            }
        });
    }
}
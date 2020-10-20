package com.example.kaffeina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText emailField, passwordField;
    Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailField  = findViewById(R.id.emailAddress);
        passwordField = findViewById(R.id.password);
        logIn = findViewById(R.id.logInButton);

        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                if(email.isEmpty()){
                    emailField.setError("Please enter an email address");
                    emailField.requestFocus();
                }
                else if(password.isEmpty()){
                    passwordField.setError("Please enter a password");
                    passwordField.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(Login.this, "Please fill in all fields and re-submit", Toast.LENGTH_SHORT).show();
                }
                else if(!email.isEmpty() && !password.isEmpty()){

                }
            }
        });
    }
}
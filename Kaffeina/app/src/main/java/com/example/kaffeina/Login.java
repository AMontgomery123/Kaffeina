package com.example.kaffeina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText emailField, passwordField;
    Button logIn, register;
    FirebaseAuth Gandalph;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Gandalph = FirebaseAuth.getInstance();
        emailField  = findViewById(R.id.emailAddress);
        passwordField = findViewById(R.id.password);
        logIn = findViewById(R.id.logInButton);
        register = findViewById(R.id.registerButton);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = Gandalph.getCurrentUser();

                if(mFirebaseUser != null) {
                    Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(Login.this, "Please log in", Toast.LENGTH_SHORT).show();
                }
            }
        };

        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                if(email.isEmpty()) {
                    emailField.setError("Please enter an email address");
                    emailField.requestFocus();
                }
                else if(password.isEmpty()) {
                    passwordField.setError("Please enter a password");
                    passwordField.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(Login.this, "Please fill in all fields and re-submit", Toast.LENGTH_SHORT).show();
                }
                else if(!email.isEmpty() && !password.isEmpty()) {
                    Gandalph.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task){
                           if(!task.isSuccessful()) {
                               Toast.makeText(Login.this, "Login error, please login again or sign up", Toast.LENGTH_SHORT).show();
                           }
                           else {
                               Intent intToMain = new Intent(Login.this, MainActivity.class);
                               startActivity(intToMain);
                           }
                       }
                    });
                }
                else {
                    Toast.makeText(Login.this, "Error occurred, please break glass", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intRegister = new Intent(Login.this, Register.class);
                startActivity(intRegister);
            }
        });
    }
}
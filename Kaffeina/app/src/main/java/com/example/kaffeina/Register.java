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
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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
                //return email, password, and name as strings
                //set missingFields as false
                 String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                 String name = nameField.getText().toString();
                boolean missingFields = false;
                
                //if name is empty set an error asking the user to enter a name
                if (name.isEmpty()) {
                    nameField.setError("Please enter a name");
                    nameField.requestFocus();
                    missingFields = true;
                }
                //if email is empty set an error asking the user to enter an email address
                //if the password isn't present, ask for a password
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
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

                                switch (errorCode) {
                                        //set up various errors as explained in the text given to the user
                                    case "ERROR_INVALID_CUSTOM_TOKEN":
                                        Toast.makeText(Register.this, "The custom token format is incorrect. Please check the documentation.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_CUSTOM_TOKEN_MISMATCH":
                                        Toast.makeText(Register.this, "The custom token corresponds to a different audience.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_INVALID_CREDENTIAL":
                                        Toast.makeText(Register.this, "The supplied auth credential is malformed or has expired.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_INVALID_EMAIL":
                                        Toast.makeText(Register.this, "Invalid Email, please supply a different email and re-submit", Toast.LENGTH_LONG).show();
                                        emailField.setError("The email address is badly formatted.");
                                        emailField.requestFocus();
                                        break;

                                    case "ERROR_WRONG_PASSWORD":
                                        Toast.makeText(Register.this, "The password is invalid or the user does not have a password.", Toast.LENGTH_LONG).show();
                                        passwordField.setError("password is incorrect ");
                                        passwordField.requestFocus();
                                        passwordField.setText("");
                                        break;

                                    case "ERROR_USER_MISMATCH":
                                        Toast.makeText(Register.this, "The supplied credentials do not correspond to the previously signed in user.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_REQUIRES_RECENT_LOGIN":
                                        Toast.makeText(Register.this, "This operation is sensitive and requires recent authentication. Log in again before retrying this request.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                                        Toast.makeText(Register.this, "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_EMAIL_ALREADY_IN_USE":
                                        Toast.makeText(Register.this, "The email address is already in use by another account.   ", Toast.LENGTH_LONG).show();
                                        emailField.setError("The email address is already in use by another account.");
                                        emailField.requestFocus();
                                        break;

                                    case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                                        Toast.makeText(Register.this, "This credential is already associated with a different user account.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_USER_DISABLED":
                                        Toast.makeText(Register.this, "The user account has been disabled by an administrator.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_USER_TOKEN_EXPIRED":
                                        Toast.makeText(Register.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_USER_NOT_FOUND":
                                        Toast.makeText(Register.this, "There is no user record corresponding to this identifier. The user may have been deleted.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_INVALID_USER_TOKEN":
                                        Toast.makeText(Register.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_OPERATION_NOT_ALLOWED":
                                        Toast.makeText(Register.this, "This operation is not allowed. You must enable this service in the console.", Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_WEAK_PASSWORD":
                                        Toast.makeText(Register.this, "The given password is invalid.", Toast.LENGTH_LONG).show();
                                        passwordField.setError("The password is invalid it must 6 characters at least");
                                        passwordField.requestFocus();
                                        break;

                                    default:
                                        Toast.makeText(Register.this, "Error: Could not register"+errorCode, Toast.LENGTH_LONG).show();

                                }
                                /// Toast.makeText(Register.this, "SignUp unsuccessful.  Did you sell your soul to Milhouse again?", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //get user id
                                String userID = Gandalph.getUid();
                                //set up the name and email to string
                                final String name = nameField.getText().toString();
                                final String email = emailField.getText().toString();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference userReference = database.getReference("User/"+userID);
                                User newUser = new User(userID, name, email, 0);
                                userReference.setValue(newUser);
                                startActivity(new Intent(Register.this, MainActivity.class));
                            }
                        }
                    });
                }
            }
        });
    }
}

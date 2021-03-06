package com.example.gettogether.gettogether;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class signup extends AppCompatActivity {

    //UI element variables
    private EditText usernameEditText;
    private EditText password1EditText;
    private EditText password2EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //UI elements set to variables
        usernameEditText = (EditText) findViewById(R.id.username);
        password1EditText = (EditText) findViewById(R.id.password1);
        password2EditText = (EditText) findViewById(R.id.password2);

        //Signup button
        Button actionButton = (Button) findViewById(R.id.action_button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_account();
            }
        });



    }


    private void create_account() {

        //Get string from text inputs
        String username = usernameEditText.getText().toString().trim();
        String password1 = password1EditText.getText().toString().trim();
        String password2 = password2EditText.getText().toString().trim();

        String error = null;


        //Checking if valid user and pass
        if (username.length() == 0) {
            error = "Error: Username required";
        } else if (password1.length() == 0) {
            error = "Error: Password required";
        } else if (!password1.equals(password2)) {
            error = "Error: Passwords do not match";
        }

        if (error != null) {
            Toast.makeText(signup.this,error,Toast.LENGTH_LONG).show();
            return;
        }

        //Create new user
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password1);

        //Sign up user
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    //Sign up error
                    Toast.makeText(signup.this, e.toString(), Toast.LENGTH_LONG).show();
                } else {
                    //Go to login activity
                    Intent intent = new Intent(signup.this, login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

    }
}

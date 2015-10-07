package com.example.gettogether.gettogether;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.SignUpCallback;

public class login extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Parse.initialize(this, "ECxO8Nf9zz6aUN5dHiXpX3tdsNF5RJJXTaRkNNyD", "sebcxnq3HbaCJiT0vQsX7SYtaJzaGBil8GSqhnU6");

        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);

        Button actionButton = (Button) findViewById(R.id.login_button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.logOut();

        /*
        ParseUser user = new ParseUser();
        user.setUsername("lukas");
        user.setPassword("pass");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(login.this, e.toString(), Toast.LENGTH_LONG).show();

                }
            }
        });
        */


    }

    private void login() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (e == null) {
                    Toast.makeText(login.this, "Success", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(login.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

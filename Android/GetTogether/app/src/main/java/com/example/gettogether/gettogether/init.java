package com.example.gettogether.gettogether;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseUser;

public class init extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        //Init Parse
        Parse.initialize(this, "ECxO8Nf9zz6aUN5dHiXpX3tdsNF5RJJXTaRkNNyD", "sebcxnq3HbaCJiT0vQsX7SYtaJzaGBil8GSqhnU6");

        
        //Check if user is logged in
        ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser != null) {
            //Go to main activity
            Toast.makeText(init.this, currentUser.getUsername(), Toast.LENGTH_LONG).show();

        } else {
            Intent intent = new Intent(init.this, login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
}
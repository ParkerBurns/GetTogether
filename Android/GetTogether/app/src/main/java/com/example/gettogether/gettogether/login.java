package com.example.gettogether.gettogether;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.SignUpCallback;

public class login extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView loggedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Parse.initialize(this, "kpny7i9tRFfHzQ1Jw1gsN5zLz7Sm36KDASDvIkVi", "ClJAgoDmJZb1bAzkQfEidT7L5j7nzd3ANELt8EN2");

        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.username);

        loggedText = (TextView) findViewById(R.id.logged_text);

        Button actionButton = (Button) findViewById(R.id.login_button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    private void login() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        loggedText.setText("Logged in!");
    }
}

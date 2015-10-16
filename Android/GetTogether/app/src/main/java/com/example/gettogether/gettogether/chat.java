package com.example.gettogether.gettogether;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class chat extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        View usersList = findViewById(R.id.list);
        View root = usersList.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));


    }
}

package com.project.android.myyoga.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.android.myyoga.R;

import com.example.android.myyoga.R;

public class Hello extends AppCompatActivity {
    private String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /* SharedPreferences prefs = getSharedPreferences("myYoga-project", MODE_PRIVATE);
        username = prefs.getString("username", "UNKNOWN");
        TextView textView = (TextView) findViewById(R.id.nameView);
        textView.setText(username);*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);



    }
}

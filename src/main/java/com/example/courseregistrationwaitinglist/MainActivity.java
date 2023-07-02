package com.example.courseregistrationwaitinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Start button functionality;
    public void tapToStart(View view){
        // Intent to navigate to the next activity;
        Intent startButton =  new Intent(this, Student_Registration_Waiting_List.class);
        startActivity(startButton);
    }
}
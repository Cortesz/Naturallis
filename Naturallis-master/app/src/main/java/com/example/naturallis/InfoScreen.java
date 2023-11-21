package com.example.naturallis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InfoScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);
    }


    public void Close(View v){
        finish();
    }

    public void Next(View v){
        Intent i = new Intent(this, NewDiet.class);
        startActivity(i);
    }

}
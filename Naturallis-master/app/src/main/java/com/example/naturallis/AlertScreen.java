package com.example.naturallis;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

import static java.util.Objects.*;

public class AlertScreen extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_screen);
        requireNonNull(getSupportActionBar()).hide();
    }

    public void Close(View v){
        finish();
    }

    public void Next(View v){
        Intent i = new Intent(this, InfoScreen.class);
        startActivity(i);
    }



}

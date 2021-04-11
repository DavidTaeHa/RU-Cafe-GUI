package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DonutOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.donut_menu);
    }

}
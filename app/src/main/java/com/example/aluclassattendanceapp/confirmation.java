package com.example.aluclassattendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class confirmation extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        this.receiveName();


    }

    public void receiveName() {
        Intent i = getIntent();
        String name = i.getStringExtra("NAME_KEY");

        textView = findViewById(R.id.confirmation);


        textView.setText(getString(R.string.message, name));
    }

}

package com.example.a3clickyclickyversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btnA5, linkCollector, location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        linkCollector = findViewById(R.id.linkButton);
        btnA5 = findViewById(R.id.A5button);
        location = findViewById(R.id.location);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);

            }
        });

        linkCollector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinkActivity.class);
                startActivity(intent);
            }
        });

        btnA5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent A5intent = new Intent(MainActivity.this, PrimeActivity.class);
                startActivity(A5intent);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent A7intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(A7intent);
            }
        });
    }
}
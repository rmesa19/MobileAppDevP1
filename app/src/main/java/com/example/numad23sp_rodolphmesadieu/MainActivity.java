package com.example.numad23sp_rodolphmesadieu;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn1 = findViewById(R.id.button);
    Button btn2 = findViewById(R.id.button2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1.setOnClickListener(view -> Toast.makeText(MainActivity.this, "Rodolph Mesadieu\nmesadieu.r@northeastern.edu",
                Toast.LENGTH_SHORT).show());

    }
}
package com.example.a3clickyclickyversion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    Button buttonA, buttonB, buttonC, buttonD, buttonE, buttonF;
    TextView textBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        buttonE = findViewById(R.id.buttonE);
        buttonF = findViewById(R.id.buttonF);
        textBox = findViewById(R.id.textView2);

        textBox.setText(getString(R.string.textBox));

        buttonA.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textBox.setText(getString(R.string.A_button));
                    }
                }
        );

        buttonB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textBox.setText(getString(R.string.B_button));
                    }
                }
        );

        buttonC.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textBox.setText(getString(R.string.C_button));
                    }
                }
        );

        buttonD.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textBox.setText(getString(R.string.D_button));
                    }
                }
        );

        buttonE.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textBox.setText(getString(R.string.E_button));
                    }
                }
        );

        buttonF.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textBox.setText(getString(R.string.F_button));
                    }
                }
        );
    }
}



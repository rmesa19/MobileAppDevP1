package com.example.numad23sp_rodolphmesadieu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class NewActivity extends AppCompatActivity {
    Button btnA = findViewById(R.id.button5);
    Button btnB = findViewById(R.id.button3);
    Button btnC = findViewById(R.id.button4);
    Button btnD = findViewById(R.id.button7);
    Button btnE = findViewById(R.id.button8);
    Button btnF = findViewById(R.id.button6);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        ConstraintLayout constraintLayout = new ConstraintLayout(this);


        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);



    }

}
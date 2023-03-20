package com.example.a3clickyclickyversion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class PrimeActivity extends AppCompatActivity {
    private static final String CURRENT_NUMBER = "current_number";
    private static final String PACIFIER_SWITCH = "pacifier_switch";
    private Button terminateSearchButton;
    private Button primeButton;
    private CheckBox pacifierSwitchCheckbox;
    private TextView currentNumberTextView;
    private TextView latestPrimeTextView;
    private Thread workerThread;
    private volatile boolean keepRunning = true;
    private long currentNumber;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);

        terminateSearchButton = findViewById(R.id.termSearchButton);
        pacifierSwitchCheckbox = findViewById(R.id.pSwitch);
        currentNumberTextView = findViewById(R.id.currentNumView);
        latestPrimeTextView = findViewById(R.id.LatestPrimeView);

        if (savedInstanceState != null) {
            currentNumber = savedInstanceState.getLong(CURRENT_NUMBER);
            pacifierSwitchCheckbox.setChecked(savedInstanceState.getBoolean(PACIFIER_SWITCH));
        } else {
            currentNumber = 3;
        }

        terminateSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keepRunning = false;
            }
        });

        findViewById(R.id.primesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keepRunning = true;
                workerThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (keepRunning) {
                            boolean isPrime = true;
                            for (long i = 2; i <= Math.sqrt(currentNumber); i++) {
                                if (currentNumber % i == 0) {
                                    isPrime = false;
                                    break;
                                }
                            }
                            if (isPrime) {
                                final long latestPrime = currentNumber;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        latestPrimeTextView.setText(String.valueOf(latestPrime));
                                    }
                                });
                            }
                            long threadCurrentNumber = currentNumber;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    currentNumberTextView.setText(String.valueOf(currentNumber));
                                }
                            });
                            currentNumber++;
                        }
                    }
                });
                workerThread.start();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(CURRENT_NUMBER, currentNumber);
        outState.putBoolean(PACIFIER_SWITCH, pacifierSwitchCheckbox.isChecked());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (workerThread != null && workerThread.isAlive()) {
            new AlertDialog.Builder(this)
                    .setTitle("Terminate Search?")
                    .setMessage("Are you sure you want to terminate the search?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            workerThread.interrupt();
                            PrimeActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        keepRunning = false;
        super.onDestroy();
        if (workerThread != null) {
            workerThread.interrupt();
        }
    }
}
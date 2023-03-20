package com.example.a3clickyclickyversion;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;
    TextView latitude, longitude, distanceTraveled;
    Button reset;

    Location originalLocation;

    private final static int REQUEST_CODE = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        distanceTraveled = findViewById(R.id.distanceTraveled);
        reset = findViewById(R.id.reset);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getLastLocation();
                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                originalLocation = null;
                distanceTraveled.setText("Traveled distance: ");
            }
        });

    }

    private void getLastLocation(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location currentLocation) {
                            System.out.println("Success");
                            if (currentLocation != null){
                                System.out.println("location is not null");

                                if(originalLocation == null) {
                                    System.out.println("Set original location");
                                    originalLocation = currentLocation;
                                } else {
                                    double distance = originalLocation.distanceTo(currentLocation);
                                    distanceTraveled.setText("Traveled distance: " + distance + " m");
                                }

                                latitude.setText("Latitude: "+ currentLocation.getLatitude());
                                longitude.setText("Longitude: "+ currentLocation.getLongitude());
                            }
                        }
                    });
        }else {
            askPermission();
        }
    }


    private void askPermission() {
        ActivityCompat.requestPermissions(LocationActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        if (requestCode == REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else {
                Toast.makeText(LocationActivity.this,"Permission Required",Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
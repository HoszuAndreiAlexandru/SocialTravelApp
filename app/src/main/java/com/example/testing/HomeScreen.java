package com.example.testing;

import androidx.appcompat.app.*;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.*;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;
import java.net.*;

public class HomeScreen extends Activity implements android.view.View.OnClickListener
{
    private TextView mStatusTextView;
    private ImageView profileImage;
    private Button logoutButton;
    private Button mapButton;
    private GoogleSignInAccount account = null;
    private firebaseConnection fb;
    private ArrayList<mapPin> mapPins;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mStatusTextView = findViewById(R.id.status);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            account = (GoogleSignInAccount)extras.get("user");
        }

        //singleton firebase connection

        fb = firebaseConnection.getInstance();
        firebaseConnection.connectWith(account.getId());
        //Log.w("user needs to be", "CREATED " + account.getId());
        //firebaseConnection.connectWith("nicusor");
        mapPins = firebaseConnection.getUserLocations();
        /*
        mapPin p = new mapPin();

        p.setPinName("locatiadracului");
        p.setLat(1);
        p.setLon(2);
        p.setReviewNote(50);
        p.setReviewText("best place evar");

        //firebaseConnection.addUserLocation(p);


        for(mapPin pin: mapPins)
        {
            Log.w("Pins: ", pin.toString());
        }
        */
        mapButton = findViewById(R.id.mapButton);
        mapButton.setOnClickListener(this);

        logoutButton = findViewById(R.id.log_out_button);
        mStatusTextView.setText("Welcome, " + account.getDisplayName());
        ImageView userImage = findViewById(R.id.profileImage);

        logoutButton.setOnClickListener(this);
    }

    private void logOut()
    {
        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("logout", "true");
        startActivity(intent);
    }

    private void openMap()
    {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("user", account);
        startActivity(intent);
    }

    public void onClick(android.view.View v)
    {
        switch (v.getId()) {
            case R.id.log_out_button:
                logOut();
                break;
            case R.id.mapButton:
                openMap();
                break;
        }
    }
}
package com.example.testing;

import androidx.appcompat.app.*;

import android.app.Activity;
import android.widget.*;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class HomeScreen extends Activity
{
    private TextView mStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mStatusTextView = findViewById(R.id.status);

        Bundle extras = getIntent().getExtras();
        GoogleSignInAccount account = null;
        if (extras != null) {
            account = (GoogleSignInAccount)extras.get("user");
        }

        mStatusTextView.setText("Welcome, " + account.getDisplayName());

    }
}
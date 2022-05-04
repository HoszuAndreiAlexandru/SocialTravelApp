package com.example.testing;

import androidx.appcompat.app.*;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.widget.*;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.io.InputStream;
import java.util.Objects;
import java.util.stream.Stream;
import java.net.*;

public class HomeScreen extends Activity
{
    private TextView mStatusTextView;
    private ImageView profileImage;

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

        ImageView userImage = findViewById(R.id.profileImage);

        try
        {
            //InputStream in = new URL(account.getPhotoUrl().toString()).openStream();

            //userImage.setImageBitmap(BitmapFactory.decodeStream(in));

            //System.out.println("URL is: " + account.getPhotoUrl());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
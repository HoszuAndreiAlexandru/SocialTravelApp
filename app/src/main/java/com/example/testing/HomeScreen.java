package com.example.testing;

import androidx.appcompat.app.*;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.widget.*;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import java.io.InputStream;
import java.util.Objects;
import java.util.stream.Stream;
import java.net.*;

public class HomeScreen extends Activity implements android.view.View.OnClickListener
{
    private TextView mStatusTextView;
    private ImageView profileImage;
    private Button logoutButton;
    private GoogleSignInAccount account = null;
    //private GoogleSignInClient googleAPI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mStatusTextView = findViewById(R.id.status);

        Bundle extras = getIntent().getExtras();
        account = null;
        if (extras != null) {
            account = (GoogleSignInAccount)extras.get("user");
            //googleAPI = (GoogleSignInClient) getIntent().getSerializableExtra("googleAPI");
        }

        logoutButton = findViewById(R.id.log_out_button);
        mStatusTextView.setText("Welcome, " + account.getDisplayName());
        ImageView userImage = findViewById(R.id.profileImage);

        logoutButton.setOnClickListener(this);

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

    private void logOut()
    {
        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("logout", "true");
        startActivity(intent);
    }

    public void onClick(android.view.View v) {
        switch (v.getId()) {
            case R.id.log_out_button:
                logOut();
                break;
        }
    }
}
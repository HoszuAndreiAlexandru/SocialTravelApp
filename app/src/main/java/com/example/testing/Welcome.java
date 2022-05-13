package com.example.testing;

import android.os.Bundle;
import android.content.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.auth.api.signin.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Parcelable;
import android.util.*;

import java.io.Serializable;

public class Welcome extends FragmentActivity implements android.view.View.OnClickListener, Serializable
{
    private com.google.android.gms.common.SignInButton button;
    private GoogleSignInClient mGoogleSignInClient;
    private static GoogleSignInOptions gso;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "SignInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //coment pt diana
        setContentView(R.layout.main_page);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        button = findViewById(R.id.sign_in_button);
        button.setOnClickListener(this);

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null)
        {
            Bundle extras = getIntent().getExtras();
            if(extras != null && extras.get("logout").equals("true"))
            {
                mGoogleSignInClient.signOut();
            }
            else
            {
                Intent intent = new Intent(this, HomeScreen.class);
                //intent.putExtra("googleAPI", (Serializable) mGoogleSignInClient);
                intent.putExtra("user", account);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN)
        {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            //
            com.google.android.gms.tasks.Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(com.google.android.gms.tasks.Task<GoogleSignInAccount> completedTask)
    {
        try
        {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            //updateUI(account);
            Intent intent = new Intent(this, HomeScreen.class);

            intent.putExtra("user", account);

            startActivity(intent);

        }
        catch (ApiException e)
        {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            //Log.w(TAG, e.getStatus().toString());
            //updateUI(null);
        }
    }

    private void signIn()
    {
        android.content.Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onClick(android.view.View v)
    {
        signIn();
    }
}

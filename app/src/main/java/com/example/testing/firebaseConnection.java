package com.example.testing;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class firebaseConnection
{
    private static final firebaseConnection connectionInstance = new firebaseConnection();
    private static FirebaseDatabase database = FirebaseDatabase.getInstance("https://travellio-6a1d4-default-rtdb.europe-west1.firebasedatabase.app/");
    private static DatabaseReference user;
    private static DatabaseReference userFriends;
    private static DatabaseReference userSettings;

    private firebaseConnection(){}

    public static boolean connectWith(String username)
    {

        user = database.getReference(username);
        //userLocations = user.child("savedlocations");
        userFriends = user.child("friends");
        userSettings = user.child("settings");
        return true;
    }

    public static ArrayList<mapPin> getUserLocations()
    {
        DatabaseReference userLocations = user.child("savedLocations");
        ArrayList<mapPin> locations = new ArrayList<>();

        Task<DataSnapshot> t = userLocations.get();

        //wait for task to complete the data fetching from firebase
        while(!t.isSuccessful()){}

        String result = t.getResult().getValue().toString();
        System.out.println(t.getResult());
        //result = result.substring(1, result.length() - 1);

        //String results[] = result.split(", location");
        //result.

        //for(int i = 0; i < results.length; i++)
        //{
            //System.out.println(results[i]);
            //locations.add(new mapPin());
        //}

        return locations;
    }

    public static firebaseConnection getInstance()
    {
        return connectionInstance;
    }
    /*
    private firebaseConnection(String username)
    {
        //username is user's email
        user = database.getReference(username);
        userLocations = user.child("savedlocations");
        userFriends = user.child("friends");
        userSettings = user.child("settings");
    }
    */

}

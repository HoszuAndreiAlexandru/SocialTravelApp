package com.example.testing;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class firebaseConnection
{
    private static final firebaseConnection connectionInstance = new firebaseConnection();
    private static FirebaseDatabase database = FirebaseDatabase.getInstance("https://travellio-6a1d4-default-rtdb.europe-west1.firebasedatabase.app/");
    private static DatabaseReference user;

    private firebaseConnection(){}

    public static void connectWith(String username)
    {
        System.out.println("connecting...");
        user = database.getReference(username);
        try
        {
            database.getReference(username).get().getResult().getChildren();
        }
        catch (Exception e)
        {
            Log.w("user needs to be", "CREATED " + username);
            //database.getReference().child("username").setValue(username);
            //user = database.getReference("username");
            database.getReference(username).child("friends").setValue("");
            database.getReference(username).child("savedLocations").setValue("");
            database.getReference(username).child("settings").setValue("");
        }
    }

    public static ArrayList<mapPin> getUserLocations()
    {
        DatabaseReference userLocations = user.child("savedLocations");
        ArrayList<mapPin> locations = new ArrayList<>();
        Task<DataSnapshot> t = userLocations.get();

        //wait for task to complete the data fetching from firebase
        while(!t.isSuccessful()){}

        Iterable<DataSnapshot> locatii = t.getResult().getChildren();

        for(DataSnapshot location: locatii)
        {
            String locationName = location.getKey();
            double latitude = Double.parseDouble(location.child("latitude").getValue().toString());
            double longitude = Double.parseDouble(location.child("longitude").getValue().toString());
            int reviewNote = Integer.parseInt(location.child("givenReview").getValue().toString());
            String comment = location.child("comment").getValue().toString();
            locations.add(new mapPin(locationName, latitude, longitude, reviewNote, comment));
        }

        return locations;
    }

    public static void addUserLocation(mapPin pin)
    {
        //TODO
    }

    public static firebaseConnection getInstance()
    {
        return connectionInstance;
    }
}

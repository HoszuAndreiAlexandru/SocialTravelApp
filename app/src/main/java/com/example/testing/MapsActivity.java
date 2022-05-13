package com.example.testing;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.testing.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.*;

import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private GoogleSignInAccount account = null;
    private HashMap<String, mapPin> pins = new HashMap<>();
    private firebaseConnection fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            account = (GoogleSignInAccount)extras.get("user");

            FirebaseDatabase database = FirebaseDatabase.getInstance("https://travellio-6a1d4-default-rtdb.europe-west1.firebasedatabase.app/");
            DatabaseReference myRef = database.getReference("nicusor");
            myRef = myRef.child("savedLocations");

            Task<DataSnapshot> t;
            String key = "location";
            boolean CONTINUE = true;
            int i = 1;

            while(CONTINUE)
            {
                t = myRef.child(key + ("" + i)).get();

                while(!t.isSuccessful())
                {
                }

                if(!t.getResult().exists())
                {
                    CONTINUE = false;
                    //Log.i("FIREBASE DA", "nu");
                }
                else
                {
                    //Log.i("FIREBASE DA", t.getResult().toString() + "     " + key + ("" + i));
                    String comment = (String)t.getResult().child("comment").getValue();
                    long givenReview = (long)t.getResult().child("givenReview").getValue();
                    double latitude = (double)t.getResult().child("latitude").getValue();
                    double longitude = (double)t.getResult().child("longitude").getValue();
                    //Log.i("FIREBASE DA ", comment);
                    pins.put(key + ("" + i), new mapPin(key + ("" + i), latitude, longitude, givenReview, comment));
                    i++;
                }
            }

            for(String cheie: pins.keySet())
            {
                //Log.i("", "");
                //Log.i("", "");
                //Log.i(cheie, pins.get(cheie).getLat() + " " + pins.get(cheie).getLon());
                //Log.i("", "");
                //Log.i("", "");
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng bucharest = new LatLng(44.43145090013488, 26.099991590860615);
        mMap.addMarker(new MarkerOptions().position(bucharest).title("Marker in Bucharest"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bucharest));

        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }
}
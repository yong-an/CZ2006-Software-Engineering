package com.example.chasexplorer;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Pass Over By Using Parcel
         /* Bundle b = getIntent().getExtras();
        ArrayList<Firebase> FIREBASEDATA = b.getParcelableArrayList("FBDATA");
        if(b != null){
            Log.d(TAG, "Data Transfer Successful \n" + FIREBASEDATA);
        }
        else {
            Log.d(TAG, "Data Not Transfered \n" + FIREBASEDATA);
        }*/

        ArrayList<Firebase> NEWDATA = FirebaseController.passMeAllData();
        Log.d(TAG, "NEW DATA BOY \n" + NEWDATA);

        LatLng Singapore = new LatLng(-1.290270, 103.851959);
        mMap.addMarker(new MarkerOptions().position(Singapore).title("Marker in Singapore"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Singapore));
    }
}

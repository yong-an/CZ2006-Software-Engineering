package com.example.chasexplorer;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
        AppCompatImageButton viewClinicBtn = (AppCompatImageButton) findViewById(R.id.ViewClinics);
        viewClinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked View Clinics Button");
                Intent i = new Intent(MapsActivity.this,ViewClinicActivity.class);
                MapsActivity.this.startActivity(i);

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng SGLatLng = new LatLng(1.3521,103.8198);// Singapore Latitude and Longitude
        float zoom = 10;// whatever

        ArrayList<Firebase> NEWDATA = FirebaseController.passMeAllData();
        Log.d(TAG, "CURRENT DATA: \n" + NEWDATA);

        //NEED ERROR CHECKING HERE FOR CASES WITH NO INTERNET
        //BASED ON FUNCTIONAL REQUIREMENTS
       try{
           for (Firebase fb : NEWDATA) {
               LatLng Clinic = new LatLng(fb.getXCoordinate(),fb.getYCoordinate());
               mMap.addMarker(new MarkerOptions().position(Clinic).title(fb.getHCIName()));
               mMap.moveCamera(CameraUpdateFactory.newLatLng(Clinic));
               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SGLatLng, zoom));
           }
       }catch(Exception e) {
           Log.d(TAG, "ERROR In DATA: \n" + e);
       }

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MapsActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}

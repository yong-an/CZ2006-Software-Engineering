package com.example.chasexplorer.Boundary;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;


import com.example.chasexplorer.Controller.MapAdapter;
import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.gson.Gson;



public class ClinicMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapAdapter mController;
    private Clinic clinicDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_map);
        mController = new MapAdapter();
        String jsonMyObject = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("clinicObj");
        }
        clinicDetails = new Gson().fromJson(jsonMyObject, Clinic.class);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        AppCompatImageButton backBtn = (AppCompatImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                ClinicMapActivity.this.finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap = mController.getClinicLocation(mMap,clinicDetails);
    }

}

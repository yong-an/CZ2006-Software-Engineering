package com.example.chasexplorer.Boundary;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.chasexplorer.Controller.MapAdapter;
import com.example.chasexplorer.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapAdapter mController;
    //private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mController = new MapAdapter();
        //searchView = findViewById(R.id.sv_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        View locationButton = ((View) mapFragment.getView().findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rlp.setMargins(0, 0, 30, 250);

        /* searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if(location != null || !location.equals("")){
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location,1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        }); */

        AppCompatImageButton viewClinicBtn = (AppCompatImageButton) findViewById(R.id.viewClinicsBtn);
        viewClinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked View Clinics Button");
                Intent i = new Intent(MapsActivity.this,ViewClinicActivity.class);
                MapsActivity.this.startActivity(i);
            }
        });
        AppCompatImageButton meBtn = (AppCompatImageButton) findViewById(R.id.meBtn);
        meBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked Login Button");
                Intent i = new Intent(MapsActivity.this,LoginActivity.class);
                MapsActivity.this.startActivity(i);

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap = mController.getGmap(mMap);
        getGPSPermission();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Long press on back button to exit the app!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                            homeIntent.addCategory( Intent.CATEGORY_HOME );
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(homeIntent);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"User granted GPS permission!", Toast.LENGTH_SHORT).show();
                    mMap.setMyLocationEnabled(true);
                } else {
                    Toast.makeText(getApplicationContext(),"User did not grant GPS permission!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @TargetApi(23)
    private void getGPSPermission(){
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            Toast.makeText(getApplicationContext(),"GPS permission is not granted!", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION   },1
            );
        } else {
            // permission already granted previously
            mMap.setMyLocationEnabled(true);
        }

    }
}

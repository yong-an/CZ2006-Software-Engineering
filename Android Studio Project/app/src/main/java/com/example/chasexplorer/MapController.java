package com.example.chasexplorer;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MapController {
    private static GoogleMap gmap;

    public MapController (){
        this.gmap = null;
    }

    public void setGmap(GoogleMap gmap) {
        this.gmap = gmap;
    }

    public GoogleMap getGmap(GoogleMap mMap){
        setGmap(mMap);
        LatLng SGLatLng = new LatLng(1.3521,103.8198);// Singapore Latitude and Longitude
        float zoom = 10;// whatever

        ArrayList<Clinic> NEWDATA = FirebaseController.passMeAllData();
        Log.d(TAG, "CURRENT DATA: \n" + NEWDATA);

        //NEED ERROR CHECKING HERE FOR CASES WITH NO INTERNET
        //BASED ON FUNCTIONAL REQUIREMENTS
        try{
            for (Clinic fb : NEWDATA) {
                LatLng Clinic = new LatLng(fb.getXCoordinate(),fb.getYCoordinate());
                gmap.addMarker(new MarkerOptions().position(Clinic).title(fb.getClinicName()));
                gmap.moveCamera(CameraUpdateFactory.newLatLng(Clinic));
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(SGLatLng, zoom));
            }
        }catch(Exception e) {
            Log.d(TAG, "ERROR In DATA: \n" + e);
        }
        return gmap;
    }
}

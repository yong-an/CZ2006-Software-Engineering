package com.example.chasexplorer.Controller;

import android.util.Log;

import com.example.chasexplorer.Entity.Clinic;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MapAdapter {

    private static GoogleMap gmap;
    private static List<Marker> markers = new ArrayList<>();
    private ArrayList<Clinic> CLINICDATA = ClinicAdapter.passMeAllData();

    public MapAdapter(){
        this.gmap = null;
    }

    public void setGmap(GoogleMap gmap) {
        this.gmap = gmap;
    }

    public GoogleMap getGmap(GoogleMap mMap){
        setGmap(mMap);
        LatLng SGLatLng = new LatLng(1.3521,103.8198);// Singapore Latitude and Longitude
        float zoom = 10;// whatever

        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(SGLatLng, zoom));
        return gmap;
    }

    public GoogleMap getGmapWithGPS(GoogleMap mMap){

        return gmap;
    }

    public void revealMarkers(GoogleMap mMap, LatLng LL){

    }



    public GoogleMap getClinicLocation(GoogleMap mMap, Clinic clinicDetails){
        setGmap(mMap);
        try{
            LatLng clinic = new LatLng(clinicDetails.getXCoordinate(),clinicDetails.getYCoordinate());
            gmap.addMarker(new MarkerOptions().position(clinic).title(clinicDetails.getClinicName()));
            gmap.moveCamera(CameraUpdateFactory.newLatLng(clinic));
            gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(clinic, 17));
        }catch(Exception e) {
            Log.d(TAG, "ERROR In DATA: \n" + e);
        }
        return gmap;
    }



    public boolean plotSearchMarkers (String Query){
        return false;
    }

    private static boolean isMyQueryValid(String str) {
        return false;
    }

}

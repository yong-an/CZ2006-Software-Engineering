package com.example.chasexplorer.Controller;

import android.util.Log;

import com.example.chasexplorer.Entity.Clinic;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MapAdapter {

    private static GoogleMap gmap;
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

        Log.d(TAG, "CURRENT DATA: \n" + CLINICDATA);

        try{
            for (Clinic fb : CLINICDATA) {
                LatLng Clinic = new LatLng(fb.getXCoordinate(),fb.getYCoordinate());
                gmap.addMarker(new MarkerOptions().position(Clinic).title(fb.getClinicName()));
                gmap.moveCamera(CameraUpdateFactory.newLatLng(Clinic));
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(SGLatLng, zoom));
            }
        }catch(Exception e) {
            Log.d(TAG, "ERROR In DATA: \n" + e);
        }

        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(SGLatLng, zoom));
        return gmap;
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


    public void plotSearchMarkers (String Query){

        if(Query.isEmpty())
            return;

        boolean check;
        boolean existTelNo, existClinicName;
        int postalCode = -1;

        check = isMyQueryAllInteger(Query);

        if(check == true)
            postalCode = Integer.parseInt(Query);

        CharSequence subQuery = Query.toLowerCase();

        for (Clinic fb : CLINICDATA){
            LatLng location = new LatLng(fb.getXCoordinate(),fb.getYCoordinate());

            existClinicName = fb.getClinicName().toLowerCase().contains(subQuery);
            existTelNo = fb.getClinicTelNo().toLowerCase().contains(subQuery);

            if(existTelNo == true || existClinicName == true)
                gmap.addMarker(new MarkerOptions().position(location).title(fb.getClinicName()));
            
            
            if(fb.getPostalCode() == postalCode)
                gmap.addMarker(new MarkerOptions().position(location).title(fb.getClinicName()));

        }
    }

    public static boolean isMyQueryAllInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

}

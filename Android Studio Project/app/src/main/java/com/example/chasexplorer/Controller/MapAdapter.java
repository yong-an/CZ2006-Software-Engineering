package com.example.chasexplorer.Controller;

import android.location.Location;
import android.util.Log;

import com.example.chasexplorer.Entity.Clinic;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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

        try{
            MarkerOptions markerOptions = new MarkerOptions();

            for (Clinic fb : CLINICDATA) {
                LatLng Clinic = new LatLng(fb.getXCoordinate(),fb.getYCoordinate());
                markerOptions.position(Clinic);
                markerOptions.title(fb.getClinicName());
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

                gmap.addMarker(markerOptions);
                gmap.moveCamera(CameraUpdateFactory.newLatLng(Clinic));
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(SGLatLng, zoom));
            }
        }catch(Exception e) {
            Log.d(TAG, "ERROR In DATA: \n" + e);
        }

        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(SGLatLng, zoom));
        return gmap;
    }

    public GoogleMap getGmapWithGPS(GoogleMap mMap){
        setGmap(mMap);
        try{
            MarkerOptions markerOptions = new MarkerOptions();

            for (Clinic fb : CLINICDATA) {
                LatLng locationClinic = new LatLng(fb.getXCoordinate(),fb.getYCoordinate());

                markerOptions.position(locationClinic);
                markerOptions.title(fb.getClinicName());
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                Marker m = gmap.addMarker(markerOptions.visible(false));
                markers.add(m);
            }

        }catch(Exception e) {
            Log.d(TAG, "ERROR In DATA: \n" + e);
        }

        return gmap;
    }

    public void revealMarkers(GoogleMap mMap, LatLng LL){

        for(int i = 0; i < markers.size(); i++){
            if (SphericalUtil.computeDistanceBetween(LL, markers.get(i).getPosition()) < 1000) {
                markers.get(i).setVisible(true);
            }
        }
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

        MarkerOptions markerOptions = new MarkerOptions();

        for (Clinic fb : CLINICDATA){
            LatLng clinicLocation = new LatLng(fb.getXCoordinate(),fb.getYCoordinate());
            existClinicName = fb.getClinicName().toLowerCase().contains(subQuery);
            existTelNo = fb.getClinicTelNo().toLowerCase().contains(subQuery);

            if(existTelNo == true || existClinicName == true){
                markerOptions.position(clinicLocation);
                markerOptions.title(fb.getClinicName());
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                gmap.addMarker(markerOptions);
            }

            if(fb.getPostalCode() == postalCode){
                markerOptions.position(clinicLocation);
                markerOptions.title(fb.getClinicName());
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                gmap.addMarker(markerOptions);
            }
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

package com.example.chasexplorer.Controller;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chasexplorer.Boundary.MapsActivity;
import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.Entity.Review;
import com.example.chasexplorer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ClinicAdapter extends AppCompatActivity {

    private static ArrayList<Clinic> FIREBASEDATA;
    private static ArrayList<Clinic> ORIGINALUNSORTED; // need the index of the original arraylist for saving purpose as DB is in unsorted manner
    private Handler mHandler;
    private Runnable mRunnable;
    private ProgressBar mProgressBar;
    private ObjectAnimator progressAnimator;

    /**
     * Android Activity default constructor.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", 0, 98);
        startAnimation();
        // Connection to Clinic
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        if(database == null)
            database.setPersistenceEnabled(true);
        final DatabaseReference myRef = database.getReference();
        myRef.keepSynced(true);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long Rows = dataSnapshot.getChildrenCount();
                Log.d(TAG,"No Of Data Rows: " + Rows);
                ArrayList<Clinic> t = new ArrayList<Clinic>();
                List<Review> reviewArrayList = new ArrayList<Review>();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Clinic chasClinic = postSnapshot.getValue(Clinic.class);
                    for(DataSnapshot reviews: postSnapshot.child("reviewAl").getChildren()) {
                        Review clinicReview = reviews.getValue(Review.class);
                        reviewArrayList.add(clinicReview);
                    }
                    t.add(chasClinic);
                }
                ReviewAdapter.setReviewAl((ArrayList) reviewArrayList);
                ORIGINALUNSORTED =(ArrayList<Clinic>) t.clone();
                FIREBASEDATA = (ArrayList<Clinic>) t.clone();
                Collections.sort(FIREBASEDATA, new Comparator<Clinic>(){
                    public int compare(Clinic clinic1, Clinic clinic2) {
                        // ## Ascending order
                        return clinic1.getClinicName().compareToIgnoreCase(clinic2.getClinicName()); // To compare string values

                        // ## Descending order
                        //return clinic2.getClinicName().compareToIgnoreCase(clinic1.getClinicName()); // To compare string values
                    }
                });
                Log.d(TAG,"Pulled Data:  " + Rows);
                progressAnimator.setIntValues(100);
                progressAnimator.start();
                Intent i = new Intent(ClinicAdapter.this, MapsActivity.class);
                ClinicAdapter.this.startActivity(i);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed To Read From Clinic...", error.toException());
            }
        });

    }

    /**
     * When App closes this method will be executed
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHandler != null && mRunnable != null)
            mHandler.removeCallbacks(mRunnable);
    }

    /**
     * This method is used to animate the Splash screen loading bar
     */
    private void startAnimation(){
        progressAnimator.setDuration(2500);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
    }

    /**
     * Returns ArrayList[Clinic]
     * This method will connect to Firebase Database and retrieve all clinic data.
     */
    public static ArrayList<Clinic> passMeAllData (){ return FIREBASEDATA; }

    /** Returns the Index Of Clinic in Original ArrayList [Clinic]
     * @param clinic
     */
    public static int getIndexOfUnsortedClinicAL(Clinic clinic) {
        int index = 0;
        for (int i = 0;i < ORIGINALUNSORTED.size();i++) {
            if(ORIGINALUNSORTED.get(i).getClinicCode().equalsIgnoreCase(clinic.getClinicCode()))
                return i;
        }
        return index;
    }

    /* public static List<String> getPlaceID(Clinic c) {
        c.setClinicName(c.getClinicName().replaceAll("&amp;", "&"));
        String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36";
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + c.getXCoordinate() + "," + c.getYCoordinate() + "&radius=50&type=clinic&keyword=" + c.getClinicName() +  "&key=" + API_KEY;
        List<String> GOOGLECLINIC = new ArrayList<>();
        String isOpened = " ";

        try {
            url = url.replaceAll(" ", "%20");
            URL obj = new URL(url);
            Thread.sleep(1000);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            StringTokenizer st = new StringTokenizer(response.toString(),",");
            while(st.hasMoreTokens()) {
                String s = st.nextToken();
                if(s.contains("place_id")) {
                    s = s.replaceAll("\"place_id\" : \"","");
                    s = s.replaceAll("\"", "");
                    s = s.replaceAll(" ", "");
                    GOOGLECLINIC.add(s);
                }
                if(s.contains("opening_hours")) {
                    if(s.contains("true"))
                        isOpened = "Opened";
                    else
                        isOpened = "Closed";

                    GOOGLECLINIC.add(isOpened);
                }
            }
            //print result
            System.out.println(response.toString());
            in.close();
        } catch (Exception e) {e.printStackTrace();}
        return GOOGLECLINIC;
    } */

   /* public static List<String>  getPlaceDetails (String placeId) {
        String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36";
        String url = "https://maps.googleapis.com/maps/api/place/details/json?place_id="+placeId+"&fields=name,opening_hours&key=" + API_KEY;
        boolean weekday_text = false;
        List<String> openingHours = new ArrayList<>();

        try {
            url = url.replaceAll(" ", "%20");
            URL obj = new URL(url);
            Thread.sleep(1000);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            StringTokenizer st = new StringTokenizer(response.toString(),",");
            while(st.hasMoreTokens()) {
                String s = st.nextToken();
                if(s.contains("weekday_text")) {
                    weekday_text = true;
                    s = s.replaceAll("\"", "");
                    s = s.replaceAll(",", "");
                    openingHours.add(s);
                }
				else if ((weekday_text) && (!(s.contains("]")))) {
                    s = s.replaceAll("\"", "");
                    s = s.replaceAll(",", "");
                    openingHours.add(s);
                } else if ((weekday_text) && ((s.contains("]")))) {
                    weekday_text = false;
                    s = s.replaceAll("\"", "");
                    s = s.replaceAll(",", "");
                    openingHours.add(s);
                }
            }
            //print result
            System.out.println(response.toString());
            in.close();
            return openingHours;

        } catch (Exception e) {e.printStackTrace();}

        return openingHours;
    } */

}

package com.example.chasexplorer.Boundary;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chasexplorer.BuildConfig;
import com.example.chasexplorer.Controller.ClinicAdapter;
import com.example.chasexplorer.Controller.ReviewAdapter;
import com.example.chasexplorer.Controller.ReviewRecyclableViewAdapter;
import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.Entity.Review;
import com.example.chasexplorer.CloudEntity.PlaceDetails;
import com.example.chasexplorer.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewClinicDetailsActivity extends AppCompatActivity {
    private static FirebaseAuth firebase;
    private RatingBar mRatingBar;
    private ReviewAdapter reviewAdapter;
    private RecyclerView recyclerView;
    private ReviewRecyclableViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Review> NEWDATA;
    private TextView mRatingAmt;
    private TextView clinicTV;
    private TextView clinicStatusNow;
    private TextView todayClinicOpeningHours;
    private TextView[] allClinicSchedules;
    private String[] openingHours;
    private ArrayList<String> clinicSchedule = new ArrayList<>();
    private String clinicName;
    private String clinicCode;
    private static final String API_KEY = "AIzaSyB2EK9o2akbfD3QAFtLvXmK3Yg07k5RD70";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_clinic_details);
        String jsonMyObject = null;
        reviewAdapter = new ReviewAdapter();
        int index = 0;

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            jsonMyObject = extras.getString("clinicObj");
            index = extras.getInt("index");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        final String index1= String.valueOf(index);
        final Clinic clinicDetails = new Gson().fromJson(jsonMyObject, Clinic.class);
        clinicCode = clinicDetails.getClinicCode();
        clinicName = clinicDetails.getClinicName();

        NEWDATA = reviewAdapter.getAllFeedbackForClinic(clinicCode);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view2);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ReviewRecyclableViewAdapter(this,NEWDATA);
        recyclerView.setAdapter(mAdapter);

        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingBar.setRating(reviewAdapter.getAvgRatingForClinic(clinicDetails.getClinicCode()));
        mRatingBar.setEnabled(false);

        mRatingAmt = (TextView) findViewById(R.id.ratingTxt);
        mRatingAmt.setText("Ratings: " + reviewAdapter.getNumberOfFeedbackForClinic(clinicDetails.getClinicCode()));

        clinicTV = (TextView) findViewById(R.id.clinicDetailsTxt);
        clinicTV.setText(clinicDetails.toString());

        clinicStatusNow = (TextView) findViewById(R.id.opencloseTxt);
        todayClinicOpeningHours = (TextView) findViewById(R.id.hoursTxt);

        final String clinicTelNo = clinicDetails.getClinicTelNo();
        ImageButton callBtn = (ImageButton) findViewById(R.id.call);
        callBtn.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View r) {
                if (getCallPermission()) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + clinicTelNo));
                    ViewClinicDetailsActivity.this.startActivity(callIntent);
                }
            }

        });
        ImageButton directionBtn = (ImageButton) findViewById(R.id.direction);
        directionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("https")
                        .authority("www.google.com")
                        .appendPath("maps")
                        .appendPath("dir")
                        .appendPath("")
                        .appendQueryParameter("api", "1")
                        .appendQueryParameter("destination", clinicDetails.getXCoordinate() + "," + clinicDetails.getYCoordinate());
                String url = builder.build().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        ImageButton reviewBtn = (ImageButton) findViewById(R.id.reviews);
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {

                if (firebase.getInstance().getCurrentUser() != null) {
                    Intent i = new Intent(r.getContext(), ReviewActivity.class);
                    i.putExtra("index", String.valueOf(ClinicAdapter.getIndexOfUnsortedClinicAL(clinicDetails)));
                    i.putExtra("clinicObj", new Gson().toJson(clinicDetails));
                    r.getContext().startActivity(i);
                }
                else
                {
                    Toast.makeText(r.getContext(), "Please login to leave a review.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getOperatingHours();

        clinicStatusNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.clinic_opening_hours, null);

                TextView mondayTimings = (TextView) popupView.findViewById(R.id.mondayTimingsTxt);
                TextView tuesdayTimings = (TextView) popupView.findViewById(R.id.tuesdayTimingsTxt);
                TextView wednesdayTimings = (TextView) popupView.findViewById(R.id.wednesdayTimingsTxt);
                TextView thursdayTimings = (TextView) popupView.findViewById(R.id.thursdayTimingsTxt);
                TextView fridayTimings = (TextView) popupView.findViewById(R.id.fridayTimingsTxt);
                TextView saturdayTimings = (TextView) popupView.findViewById(R.id.saturdayTimingsTxt);
                TextView sundayTimings = (TextView) popupView.findViewById(R.id.sundayTimingsTxt);

                allClinicSchedules = new TextView[7];
                allClinicSchedules[0] = mondayTimings;
                allClinicSchedules[1] = tuesdayTimings;
                allClinicSchedules[2] = wednesdayTimings;
                allClinicSchedules[3] = thursdayTimings;
                allClinicSchedules[4] = fridayTimings;
                allClinicSchedules[5] = saturdayTimings;
                allClinicSchedules[6] = sundayTimings;

                for (int i=0; i < clinicSchedule.size(); i++)
                {
                    openingHours = clinicSchedule.get(i).split(" ", 2);
                    allClinicSchedules[i].setText(openingHours[1]);
                }

                popupView.animate();

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        mAdapter.setmDataset(reviewAdapter.getAllFeedbackForClinic(clinicCode));
        mRatingBar.setRating(reviewAdapter.getAvgRatingForClinic(clinicCode));
        mRatingAmt.setText("(" + reviewAdapter.getNumberOfFeedbackForClinic(clinicCode) +")");
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"User granted call permission! Click call again to call the clinic", Toast.LENGTH_SHORT).show();
                    // permission was granted, yay! do the
                    // calendar task you need to do.
                } else {
                    Toast.makeText(getApplicationContext(),"User did not grant call permission!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @TargetApi(23)
    private boolean getCallPermission(){
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            Toast.makeText(getApplicationContext(),"Call permission is not granted!", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(ViewClinicDetailsActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},1
            );
            return false;
        } else {
            // permission already granted previously
            return true;
        }
    }

    private void getOperatingHours()
    {
        String url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?key=" + API_KEY + "&input=" + clinicName + "&inputtype=textquery&fields=place_id";
        final RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                String placeID = "";

                JSONObject jsonObject = new JSONObject(response);
                JSONArray candidates = jsonObject.getJSONArray("candidates");

                if (candidates.length() > 0)
                    placeID = candidates.getJSONObject(0).getString("place_id").toString();


                if (!placeID.equals(""))
                {
                    String url1 = "https://maps.googleapis.com/maps/api/place/details/json?key=" + API_KEY + "&placeid=" + placeID + "&fields=opening_hours";

                    final StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url1, response1 -> {
                        Gson gson = new Gson();
                        PlaceDetails placeDetails = gson.fromJson(response1, PlaceDetails.class);
                        int today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                        clinicSchedule.addAll(placeDetails.getResult().getOpeningHours().getWeekdayText());

                        for (int i=0; i < clinicSchedule.size(); i++)
                        {
                            openingHours = clinicSchedule.get(i).split(" ", 2);

                            if (i == today){
                                String formatHours = openingHours[1];
                                formatHours = formatHours.replace(",","\n");
                                todayClinicOpeningHours.setText(" "+formatHours);
                            }
                        }


                        if (placeDetails.getResult().getOpeningHours().getOpenNow())
                        {
                            clinicStatusNow.setTextColor(Color.GREEN);
                            clinicStatusNow.setText("OPEN NOW");
                        }
                        else
                        {
                            clinicStatusNow.setTextColor(Color.RED);
                            clinicStatusNow.setText("CLOSED");
                        }

                    }, error -> Log.e("Volley", "An error occured"));

                    mRequestQueue.add(stringRequest2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("Volley2", "An error occured"));

        mRequestQueue.add(stringRequest);
    }
}

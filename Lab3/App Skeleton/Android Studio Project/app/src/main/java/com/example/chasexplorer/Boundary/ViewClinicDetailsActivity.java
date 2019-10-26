package com.example.chasexplorer.Boundary;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.RelativeLayout;
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
    private RelativeLayout clinicStatusBox;
    private static final String API_KEY = BuildConfig.ApiKey;


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
        clinicStatusBox = (RelativeLayout) findViewById(R.id.opencloseBox);
        todayClinicOpeningHours = (TextView) findViewById(R.id.hoursTxt);

        final String clinicTelNo = clinicDetails.getClinicTelNo();
        ImageButton callBtn = (ImageButton) findViewById(R.id.call);
        callBtn.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View r) {

            }

        });
        ImageButton directionBtn = (ImageButton) findViewById(R.id.direction);
        directionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {

            }
        });
        ImageButton reviewBtn = (ImageButton) findViewById(R.id.reviews);
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {

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
    }

    @TargetApi(23)
    private boolean getCallPermission(){
        return false;
    }

    private void getOperatingHours() {

    }

    private int getDayOfTheWeek(){
        return 1;
    }
}

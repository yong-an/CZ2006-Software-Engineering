package com.example.chasexplorer.Boundary;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.chasexplorer.Controller.ReviewAdapter;
import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.Entity.Review;
import com.example.chasexplorer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ReviewActivity extends AppCompatActivity {
    private RatingBar mRatingBar;
    private TextView mClinicName;
    private TextView mRatingScale;
    private EditText mFeedback;
    private Button mSendFeedback;
    private Clinic clinicDetails;
    private ReviewAdapter reviewAdapter;
    private FirebaseUser currFirebaseUser;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar2);
        mClinicName = (TextView) findViewById(R.id.clinicName);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.editTextFeedback);
        mSendFeedback = (Button) findViewById(R.id.submitFeedBack);
        reviewAdapter = new ReviewAdapter();

        //test for intent
        Intent intent = getIntent();
        final String index = intent.getStringExtra("index");
        String jsonMyObject = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("clinicObj");
        }
        clinicDetails = new Gson().fromJson(jsonMyObject, Clinic.class);
        mClinicName.setText(clinicDetails.getClinicName());
        TextView mClinicIndex = (TextView)findViewById(R.id.textViewClinicIndex);
        mClinicIndex.setText("Index of clinic: " + index);
        //getting user id
        final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        currFirebaseUser = currentFirebaseUser;
        Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        mRatingBar.setRating(reviewAdapter.getUsersRatingForClinic(currentFirebaseUser.getUid(),clinicDetails.getPostalCode()));
        mFeedback.setText(reviewAdapter.getUsersFeedbackForClinic(currentFirebaseUser.getUid(),clinicDetails.getPostalCode()));
        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = 0;
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(ReviewActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                } else {
                        result = reviewAdapter.saveReview(clinicDetails, mFeedback.getText().toString(), mRatingBar.getRating(), currentFirebaseUser.getUid(), currentFirebaseUser.getEmail(), (currentFirebaseUser.getPhotoUrl()).toString(),index, database, currentFirebaseUser.getDisplayName());
                        if (result == 1)
                            Toast.makeText(ReviewActivity.this, "Thank you for sharing your feedback! Feedback saved successfully", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(ReviewActivity.this, "Feedback not saved successfully", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

}

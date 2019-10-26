package com.example.chasexplorer.Boundary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chasexplorer.Controller.ReviewAdapter;
import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;


public class ReviewActivity extends AppCompatActivity {
    private RatingBar mRatingBar;
    private TextView mClinicName;
    private EditText mFeedback;
    private Button mSendFeedback;
    private Clinic clinicDetails;
    private ReviewAdapter reviewAdapter;
    private boolean result;
    private String DP = "https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg";

    private FirebaseUser user;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        database = FirebaseDatabase.getInstance();
        user = auth.getCurrentUser();

        retrieveUserImage();

        mRatingBar = (RatingBar) findViewById(R.id.ratingBar2);
        mClinicName = (TextView) findViewById(R.id.clinicName);
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

        //getting user id
        final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        
        mRatingBar.setRating(reviewAdapter.getUsersRatingForClinic(currentFirebaseUser.getUid(),clinicDetails.getClinicCode()));
        mFeedback.setText(reviewAdapter.getUsersFeedbackForClinic(currentFirebaseUser.getUid(),clinicDetails.getClinicCode()));

        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private void retrieveUserImage(){

    }

}

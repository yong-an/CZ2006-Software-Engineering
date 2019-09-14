package com.example.chasexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import android.os.Handler;


import static android.content.ContentValues.TAG;

public class FirebaseController extends AppCompatActivity {

    private ArrayList<Firebase> FIREBASEDATA;
    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Connection to Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long Rows = dataSnapshot.getChildrenCount();
                Log.d(TAG,"No Of Data Rows: " + Rows);

                GenericTypeIndicator<ArrayList<Firebase>> t = new GenericTypeIndicator<ArrayList<Firebase>>(){};
                FIREBASEDATA = dataSnapshot.getValue(t);

                for(int i = 0; i < FIREBASEDATA.size(); i++){
                    Log.d(TAG,"CLINIC NAMES : \n" + FIREBASEDATA.get(i).getHCIName());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed To Read From Firebase...", error.toException());
            }
        });

        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(FirebaseController.this, MapsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("FBDATA", FIREBASEDATA);
                i.putExtras(bundle);
                FirebaseController.this.startActivity(i);
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 2500);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHandler != null && mRunnable != null)
            mHandler.removeCallbacks(mRunnable);
    }

}

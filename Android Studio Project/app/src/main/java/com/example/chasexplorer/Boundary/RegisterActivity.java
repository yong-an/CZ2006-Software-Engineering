package com.example.chasexplorer.Boundary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.chasexplorer.R;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View bView = getWindow().getDecorView();
        bView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_register);

        AppCompatImageButton mapBtn = (AppCompatImageButton) findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked View Map Button");
                Intent i = new Intent(RegisterActivity.this,MapsActivity.class);
                RegisterActivity.this.startActivity(i);
            }
        });

        AppCompatImageButton viewClinicBtn = (AppCompatImageButton) findViewById(R.id.viewClinicsBtn);
        viewClinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked View Clinics Button");
                Intent i = new Intent(RegisterActivity.this,ViewClinicActivity.class);
                RegisterActivity.this.startActivity(i);
            }
        });

    }



}

package com.example.chasexplorer.Boundary;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

public class ViewClinicDetailsActivity extends AppCompatActivity {
    private static FirebaseAuth firebase;
    private static FirebaseUser loggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_clinic_details);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.clinic_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        String jsonMyObject = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("clinicObj");
        }
        final Clinic clinicDetails = new Gson().fromJson(jsonMyObject, Clinic.class);
        TextView clinicTV = (TextView) findViewById(R.id.clinicDetails);
        clinicTV.setText(clinicDetails.getClinicName() + "\n" + clinicDetails.getClinicCode() + "\n+(65)" + clinicDetails.getClinicTelNo()
                + "\n" + clinicDetails.getStreetName() + "\nBlk " + clinicDetails.getBlkHseNo() + clinicDetails.getAddrType() + " #" + clinicDetails.getFloorNo() + "-" + clinicDetails.getUnitNo()
                + "\nSingapore " + clinicDetails.getPostalCode());
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
                //Toast.makeText(r.getContext(),"Clicked View Clinic Location button!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(r.getContext(), ClinicMapActivity.class);
                i.putExtra("clinicObj", new Gson().toJson(clinicDetails));
                r.getContext().startActivity(i);
            }
        });
        ImageButton reviewBtn = (ImageButton) findViewById(R.id.reviews);
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                //loggedIn = firebase.getCurrentUser();

                if (firebase.getInstance().getCurrentUser() != null) {
                    //console.log("user id: " + firebase.auth().currentUser.uid);
                    Toast.makeText(r.getContext(), "Clicked View Clinic Review button!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(r.getContext(), ReviewActivity.class);
                    //i.putExtra("clinicObj", new Gson().toJson(clinicDetails));

                    i.putExtra("clinicObj", new Gson().toJson(clinicDetails));
                    r.getContext().startActivity(i);
                }
                else
                {
                    Toast.makeText(r.getContext(), "Not Logon, please login to make a review.", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
}

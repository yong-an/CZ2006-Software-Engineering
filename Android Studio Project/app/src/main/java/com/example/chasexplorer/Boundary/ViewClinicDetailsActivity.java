package com.example.chasexplorer.Boundary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.R;
import com.google.gson.Gson;

public class ViewClinicDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_clinic_details);
        String jsonMyObject = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("clinicObj");
        }
        Clinic clinicDetails = new Gson().fromJson(jsonMyObject, Clinic.class);
        TextView clinicTV = (TextView) findViewById(R.id.clinicDetails);
        clinicTV.setText(clinicDetails.getClinicName() + "\n" + clinicDetails.getClinicCode() + "\n+(65)" + clinicDetails.getClinicTelNo()
                + "\n" + clinicDetails.getStreetName() + "\nBlk " + clinicDetails.getBlkHseNo() + clinicDetails.getAddrType() + " #" + clinicDetails.getFloorNo() + "-" + clinicDetails.getUnitNo()
                + "\nSingapore " + clinicDetails.getPostalCode());
    }
}

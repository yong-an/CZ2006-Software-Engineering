package com.example.chasexplorer.Boundary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.chasexplorer.Controller.ClinicRecyclableViewAdapter;
import com.example.chasexplorer.Controller.FirebaseController;
import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ViewClinicActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ClinicRecyclableViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View bView = getWindow().getDecorView();
        bView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_view_clinic);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.clinic_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        ArrayList<Clinic> NEWDATA = FirebaseController.passMeAllData();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ClinicRecyclableViewAdapter(NEWDATA);
        recyclerView.setAdapter(mAdapter);

        AppCompatImageButton mapBtn = (AppCompatImageButton) findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked Map Button");
                Intent i = new Intent(ViewClinicActivity.this,MapsActivity.class);
                ViewClinicActivity.this.startActivity(i);
            }
        });
        AppCompatImageButton meBtn = (AppCompatImageButton) findViewById(R.id.meBtn);
        meBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked Login Button");
                Intent i = new Intent(ViewClinicActivity.this,LoginActivity.class);
                ViewClinicActivity.this.startActivity(i);
            }
        });
    }
}

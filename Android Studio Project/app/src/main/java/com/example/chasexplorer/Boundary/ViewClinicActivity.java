package com.example.chasexplorer.Boundary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.SearchView;

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

public class ViewClinicActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private ClinicRecyclableViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Clinic> NEWDATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clinic);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.clinic_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        NEWDATA = FirebaseController.passMeAllData();
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

        // Locate the EditText in listview_main.xml
        SearchView editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d(TAG, "Inputted text: " + newText);
        String text = newText;
        mAdapter.filter(text);
        mAdapter.notifyDataSetChanged();
        return false;
    }
}

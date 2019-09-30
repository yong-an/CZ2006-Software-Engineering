package com.example.chasexplorer.Boundary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chasexplorer.Controller.ClinicRecyclableViewAdapter;
import com.example.chasexplorer.Controller.FirebaseController;
import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.R;

import java.util.ArrayList;

public class ViewClinicActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ClinicRecyclableViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clinic);
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
    }
}

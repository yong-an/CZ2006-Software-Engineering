package com.example.chasexplorer.Boundary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.chasexplorer.Controller.ClinicRecyclableViewAdapter;
import com.example.chasexplorer.Controller.ClinicAdapter;
import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

public class ViewClinicActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private RecyclerView recyclerView;
    private ClinicRecyclableViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Clinic> NEWDATA;
    private SearchView editsearch;

    /**
     * Android Activity default constructor.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clinic);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.clinic_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        NEWDATA = ClinicAdapter.passMeAllData();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, VERTICAL);
        recyclerView.addItemDecoration(itemDecor);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ClinicRecyclableViewAdapter(NEWDATA);
        recyclerView.setAdapter(mAdapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
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

    /**
     * When back button is pressed.
     */
    @Override
    public void onBackPressed(){
        Toast.makeText(ViewClinicActivity.this,"Use the navigation buttons instead!",Toast.LENGTH_SHORT).show();
    }

    /**
     * when back button is held down.
     * @param keyCode
     * @param event
     */
    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                            homeIntent.addCategory( Intent.CATEGORY_HOME );
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(homeIntent);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    /**
     * This method check if the user has submitted their
     * search query
     * @param query
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        editsearch.clearFocus();
        return true;
    }

    /**
     * This method will check if there changes in the
     * search box e.g. when the user is typing something.
     * @param newText
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d(TAG, "Inputted text: " + newText);
        String text = newText;
        mAdapter.filter(text);
        mAdapter.notifyDataSetChanged();
        return false;
    }
}

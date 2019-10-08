package com.example.chasexplorer.Boundary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chasexplorer.Controller.FirebaseController;
import com.example.chasexplorer.Controller.LoginController;
import com.example.chasexplorer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.internal.zzn;

import static android.content.ContentValues.TAG;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView userEmail;
    private Button btnSignout;

    private LoginController lController;
    private FirebaseUser loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        lController = new LoginController();
        loggedIn = lController.currentUserLoggedIn();

        if(loggedIn == null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        userEmail = (TextView) findViewById(R.id.userEmail);
        btnSignout= (Button)findViewById(R.id.signoutBtn);

        userEmail.setText("Welcome Back \n" + loggedIn.getEmail());

        btnSignout.setOnClickListener(this);

        AppCompatImageButton mapBtn = (AppCompatImageButton) findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked View Map Button");
                Intent i = new Intent(UserProfileActivity.this,MapsActivity.class);
                UserProfileActivity.this.startActivity(i);
            }
        });

        AppCompatImageButton viewClinicBtn = (AppCompatImageButton) findViewById(R.id.viewClinicsBtn);
        viewClinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked View Clinics Button");
                Intent i = new Intent(UserProfileActivity.this,ViewClinicActivity.class);
                UserProfileActivity.this.startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View view) {

        if(view == btnSignout){
            lController.userSignout();
            finish();
            Intent i = new Intent(UserProfileActivity.this,LoginActivity.class);
            UserProfileActivity.this.startActivity(i);
        }

    }
}

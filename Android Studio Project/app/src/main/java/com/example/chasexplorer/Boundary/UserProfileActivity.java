package com.example.chasexplorer.Boundary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chasexplorer.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView userEmail;
    private Button btnSignout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        userEmail = (TextView) findViewById(R.id.userEmail);
        btnSignout= (Button)findViewById(R.id.signoutBtn);

        userEmail.setText("Welcome Back \n" + auth.getCurrentUser().getEmail());

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
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // user is now signed out
                            startActivity(new Intent(UserProfileActivity.this, LoginActivity.class));
                            finish();
                        }
                    });
        }

    }
}

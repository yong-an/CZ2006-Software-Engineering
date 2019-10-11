package com.example.chasexplorer.Boundary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.chasexplorer.R;
import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatImageButton mapBtn;
    private AppCompatImageButton viewClinicBtn;
    private Button btnLogin;

    private final static int RC_SIGN_IN = 18;

    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build());

    /* AuthMethodPickerLayout customLayout = new AuthMethodPickerLayout
            .Builder(R.layout.activity_login_2)
            .setGoogleButtonId(R.id.googleLoginBtn)
            .setEmailButtonId(R.id.emailLoginBtn)
            .build(); */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
        }

        btnLogin = (Button) findViewById(R.id.signinBtn);
        mapBtn = (AppCompatImageButton) findViewById(R.id.mapBtn);
        viewClinicBtn = (AppCompatImageButton) findViewById(R.id.viewClinicsBtn);

        btnLogin.setOnClickListener(this);

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked View Map Button");
                Intent i = new Intent(LoginActivity.this,MapsActivity.class);
                LoginActivity.this.startActivity(i);
            }
        });

        viewClinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked View Clinics Button");
                Intent i = new Intent(LoginActivity.this,ViewClinicActivity.class);
                LoginActivity.this.startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed(){
        Toast.makeText(LoginActivity.this,"Use the navigation buttons instead!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        if(view == btnLogin){
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setTheme(R.style.LoginTheme)
                            .setIsSmartLockEnabled(false)
                            .setLogo(R.drawable.fba)
                            //.setAuthMethodPickerLayout(customLayout)
                            .build(),
                    RC_SIGN_IN);
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                finish();
                Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Toast.makeText(LoginActivity.this,"Sign in cancelled",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(LoginActivity.this,"No internet connection",Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(LoginActivity.this,"Something went wrong.",Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Sign-in error: ", response.getError());
            }
        }
    }




}

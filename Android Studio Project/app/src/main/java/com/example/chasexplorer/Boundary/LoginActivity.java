package com.example.chasexplorer.Boundary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.chasexplorer.Controller.LoginController;
import com.example.chasexplorer.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText tvEmail;
    private EditText tvPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private AppCompatImageButton mapBtn;
    private AppCompatImageButton viewClinicBtn;
    private SignInButton btnGoogleLogin;

    private final static int RC_SIGN_IN = 1;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;

    private boolean status;
    private String error;

    private ProgressDialog loading;
    private LoginController lController;
    private FirebaseUser loggedIn;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
                }
            }
        };

        loggedIn = lController.currentUserLoggedIn();
        if(loggedIn != null){
            finish();
            startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
        }

        tvEmail = (EditText)findViewById(R.id.emailText);
        tvPassword = (EditText)findViewById(R.id.passText);
        btnLogin = (Button)findViewById(R.id.signinBtn);
        tvRegister = (TextView)findViewById(R.id.registerBtn);
        btnGoogleLogin = (SignInButton) findViewById(R.id.googleSigninBtn);
        mapBtn = (AppCompatImageButton) findViewById(R.id.mapBtn);
        viewClinicBtn = (AppCompatImageButton) findViewById(R.id.viewClinicsBtn);

        loading = new ProgressDialog(this);
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        btnGoogleLogin.setOnClickListener(this);

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

         GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

         mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginActivity.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

    }

    public void loginSync (){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                status = lController.getStatus();
                error = lController.getErrorTxt();

                if(status == true) {
                    loading.dismiss();
                    finish();
                    Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
                }
                else {
                    loading.dismiss();
                    Toast.makeText(LoginActivity.this,error,Toast.LENGTH_SHORT).show();
                }
            }
        }, 1888);
    }

    @Override
    public void onClick(View view) {

        if(view == btnLogin){
            String email = tvEmail.getText().toString().trim();
            String password = tvPassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this,"Please enter a valid email",Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(this,"Please enter a valid password",Toast.LENGTH_SHORT).show();
                return;
            }

            lController.loginUser(email,password);
            loginSync();
            loading.setMessage("Logging in User...");
            loading.show();

        }

        if(view == tvRegister){
            Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
            LoginActivity.this.startActivity(i);
        }

        if(view == btnGoogleLogin){
            handOverGoogleSignIn();
        }

    }

    private void handOverGoogleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(LoginActivity.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Snackbar.make(findViewById(R.id.googleSigninBtn), "Authentication Failure.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                    }
                });
    }


}

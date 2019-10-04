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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.chasexplorer.Controller.LoginController;
import com.example.chasexplorer.R;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText tvEmail;
    private EditText tvPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private boolean status;
    private String error;
    private ProgressDialog loading;
    private LoginController lController;
    private FirebaseUser loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lController = new LoginController();
        loggedIn = lController.currentUserLoggedIn();

        if(loggedIn != null){
            finish();
            startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
        }

        tvEmail = (EditText)findViewById(R.id.emailText);
        tvPassword = (EditText)findViewById(R.id.passText);
        btnLogin = (Button)findViewById(R.id.signinBtn);
        tvRegister = (TextView)findViewById(R.id.registerBtn);

        loading = new ProgressDialog(this);
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);

        AppCompatImageButton mapBtn = (AppCompatImageButton) findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked View Map Button");
                Intent i = new Intent(LoginActivity.this,MapsActivity.class);
                LoginActivity.this.startActivity(i);
            }
        });

        AppCompatImageButton viewClinicBtn = (AppCompatImageButton) findViewById(R.id.viewClinicsBtn);
        viewClinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked View Clinics Button");
                Intent i = new Intent(LoginActivity.this,ViewClinicActivity.class);
                LoginActivity.this.startActivity(i);
            }
        });

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
                    Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                    finish();
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

    }
}

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

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener{

    private TextView tvReturn;
    private EditText tvEmail;
    private EditText tvPassword;
    private Button btnRegister;
    private boolean status;
    private String error;
    private ProgressDialog loading;
    private LoginController lController;
    private FirebaseUser loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        lController = new LoginController();
        if(loggedIn != null){
            finish();
            startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
        }

        tvReturn = (TextView)findViewById(R.id.btnReturn);
        tvEmail = (EditText)findViewById(R.id.emailText);
        tvPassword = (EditText)findViewById(R.id.passText);
        btnRegister = (Button)findViewById(R.id.signupBtn);

        loading = new ProgressDialog(this);
        tvReturn.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        AppCompatImageButton mapBtn = (AppCompatImageButton) findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked View Map Button");
                Intent i = new Intent(RegisterActivity.this,MapsActivity.class);
                RegisterActivity.this.startActivity(i);
            }
        });

        AppCompatImageButton viewClinicBtn = (AppCompatImageButton) findViewById(R.id.viewClinicsBtn);
        viewClinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                Log.d(TAG, "Clicked View Clinics Button");
                Intent i = new Intent(RegisterActivity.this,ViewClinicActivity.class);
                RegisterActivity.this.startActivity(i);
            }
        });

    }

   public void registrationSync (){
       final Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {

               status = lController.getStatus();
               error = lController.getErrorTxt();

               if(status == true) {
                   loading.dismiss();
                   tvEmail.setText("");
                   tvPassword.setText("");
                   Toast.makeText(RegisterActivity.this,"Registration Success",Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                   RegisterActivity.this.startActivity(i);
               }
               else {
                   loading.dismiss();
                   Log.d(TAG, "3. Register ACTIVITY \n\n\n" + status + " : " + error);
                   Toast.makeText(RegisterActivity.this,error,Toast.LENGTH_SHORT).show();
               }
           }
       }, 1888);
   }

    @Override
    public void onClick(View view) {

        if(view == btnRegister){

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

            lController.registerUser(email,password);

            registrationSync();
            loading.setMessage("Registering User...");
            loading.show();

        }

        if(view == tvReturn){
            Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
            RegisterActivity.this.startActivity(i);
        }
    }
}
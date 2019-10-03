package com.example.chasexplorer.Boundary;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.concurrent.atomic.AtomicBoolean;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener{

    private TextView tvReturn;
    private EditText tvEmail;
    private EditText tvPassword;
    private Button btnRegister;
    private LoginController lController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvReturn = (TextView)findViewById(R.id.btnReturn);
        tvEmail = (EditText)findViewById(R.id.emailText);
        tvPassword = (EditText)findViewById(R.id.passText);
        btnRegister = (Button)findViewById(R.id.signupBtn);
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

   /*Private void register(){

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(RegisterActivity.this,"Registration Success",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                            RegisterActivity.this.startActivity(i);
                        }
                        else{
                            errorTxt= task.getException().getMessage();
                            Toast.makeText(RegisterActivity.this,errorTxt,Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }*/

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

            lController = new LoginController();
            lController.registerUser(email,password);

            boolean status = lController.getStatus();
            String error = lController.getErrorTxt();

                if(status == true) {
                    Toast.makeText(RegisterActivity.this,"Registration Success",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                    RegisterActivity.this.startActivity(i);
                }
                else {
                    Log.d(TAG, "3. Register ACTIVITY \n\n\n" + status + " : " + error);
                    Toast.makeText(RegisterActivity.this,error,Toast.LENGTH_SHORT).show();
                }

        }

        if(view == tvReturn){
            Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
            RegisterActivity.this.startActivity(i);
        }
    }
}

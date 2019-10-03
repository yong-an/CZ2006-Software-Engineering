package com.example.chasexplorer.Controller;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.atomic.AtomicBoolean;

import static android.content.ContentValues.TAG;

public class LoginController {

    private static FirebaseAuth firebaseAuth;
    private static String errorTxt = "";
    private static boolean status;

    public LoginController(){
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void registerUser(String email, String password) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    status = true;
                } else {
                    status = false;
                    errorTxt = task.getException().getMessage();
                    Log.d(TAG, "1. TASK IN METHOD \n\n\n" + status + " : " + errorTxt);
                }
            }
        });
        Log.d(TAG, "2. METHOD \n\n\n" + status + " : " + errorTxt);
}

    public static String getErrorTxt(){return errorTxt;}
    public static boolean getStatus() {return status;}
}

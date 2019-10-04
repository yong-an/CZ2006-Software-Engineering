package com.example.chasexplorer.Controller;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginController {

    private static FirebaseAuth firebaseAuth;
    private static String errorTxt = "";
    private static boolean status;
    private static FirebaseUser loggedIn;

    public LoginController(){
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public FirebaseUser currentUserLoggedIn(){
        loggedIn = firebaseAuth.getCurrentUser();
        return loggedIn;
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
                }
            }
        });

    }

    public void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    status = true;
                }
                else{
                    status = false;
                    errorTxt = task.getException().getMessage();
                }
            }
        });
    }

    public static String getErrorTxt(){return errorTxt;}
    public static boolean getStatus() {return status;}
}

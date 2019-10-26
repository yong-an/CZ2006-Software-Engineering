package com.example.chasexplorer.Boundary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chasexplorer.R;
import com.firebase.ui.auth.AuthUI;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;

import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


import static android.content.ContentValues.TAG;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView userEmail;
    private TextView userName;
    private TextView userPassword;
    private CircularImageView userDisplayPicture;
    private Button btnSignout;
    private Uri filePath;
    public static final int PICK_IMAGE = 1;

    private FirebaseUser user;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    /**
     * Android Activity default constructor.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        user = auth.getCurrentUser();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        downloadImage();

        if (user == null) {
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        userName = (TextView) findViewById(R.id.userName);
        userEmail = (TextView) findViewById(R.id.userEmail);
        userPassword = (TextView) findViewById(R.id.userPassword);
        userDisplayPicture = (CircularImageView) findViewById(R.id.userDP);
        btnSignout= (Button)findViewById(R.id.signoutBtn);

        userEmail.setText(user.getEmail());
        userName.setText(user.getDisplayName());

        userDisplayPicture.setOnClickListener(this);
        btnSignout.setOnClickListener(this);

        userPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.activity_change_password, null);
                popupView.animate();

                EditText PWF = (EditText) popupView.findViewById(R.id.oldPassword);
                EditText NPWF = (EditText) popupView.findViewById(R.id.newPassword);
                Button submitBtn = (Button) popupView.findViewById(R.id.btnSubmit);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String PW = PWF.getText().toString();
                        String NPW = NPWF.getText().toString();

                        if(PW.equals("") || NPW.equals("")){
                            Toast.makeText(getApplicationContext(),"Please fill up the required information", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), PW);

                            user.reauthenticate(credential)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                user.updatePassword(NPW).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            popupWindow.dismiss();
                                                            PWF.getText().clear();
                                                            NPWF.getText().clear();
                                                            Toast.makeText(getApplicationContext(),"Password updated!", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            String errorTxt = task.getException().getMessage();
                                                            Toast.makeText(getApplicationContext(),errorTxt, Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            } else {
                                                Toast.makeText(getApplicationContext(),"Your current password is wrong", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }

                    }
                });

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

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

    /**
     * All On Click functions, depending on what the user tap on screen.
     * it will execute the appropriate methods.
     * @param view
     */
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

        if(view == userDisplayPicture){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        }

    }

    /**
     * Android Activity results feedback function
     * depending on your results obtained you might want to feedback different
     * logic to your users.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {

            if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null ) {
                filePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    userDisplayPicture.setImageBitmap(bitmap);
                    uploadImage();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This method will upload user selected image as display picture
     * into the Firebase User Database
     */
    private void uploadImage() {

        if(filePath != null)
        {

            StorageReference ref = storageReference.child("images/"+ user.getUid() + "/" + "profile.png");
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(UserProfileActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UserProfileActivity.this, "Something Went Wrong!\n"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("Upload:",e.getMessage());
                        }
                    });
        }
    }

    /**
     * This method will search the Firebase User Database for the current user
     * and retrieve their profile picture.
     */
    private void downloadImage(){
        storageReference.child("images/"+ user.getUid() + "/" + "profile.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Bitmap DP = convertImageBitmap(uri.toString());
                userDisplayPicture.setImageBitmap(DP);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Download:",e.getMessage());
            }
        });
    }

    /**
     * this method will convert a URL into a Bitmap file
     * @param url
     * @return
     */
    private Bitmap convertImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "Error getting bitmap", e);
        }
        return bm;
    }

    /**
     * When back button is pressed.
     */
    @Override
    public void onBackPressed(){
        Toast.makeText(UserProfileActivity.this,"Use the navigation buttons instead!",Toast.LENGTH_SHORT).show();
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
}

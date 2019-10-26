package com.example.chasexplorer.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements  Parcelable {

    private float rating;
    private String feedbackText;
    private String uid;
    private String displayName;
    private String email;
    private String photoUrl;
    private String clinicCode;

    public Review(){
        // Empty constructor for Firebase
    }

    public Review(float rating, String feedbackText, String uid, String displayName, String email, String photoUrl, String clinicCode) {
        this.rating = rating;
        this.feedbackText = feedbackText;
        this.uid = uid;
        this.displayName = displayName;
        this.email = email;
        this.photoUrl = photoUrl;
        this.clinicCode = clinicCode;
    }
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getFeedbackText() { return feedbackText; }

    public void setFeedbackText(String feedbackText) { this.feedbackText = feedbackText; }

    public String getUid() { return uid; }

    public void setUid() { this.uid = uid; }

    public String getDisplayName() { return displayName; }

    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhotoUrl() { return photoUrl; }

    public void setPhotoUrl() { this.photoUrl = photoUrl; }

    public String getClinicCode(){ return clinicCode; }

    public void setClinicCode(String clinicCode) { this.clinicCode = clinicCode; }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeFloat(rating);
        dest.writeString(feedbackText);
        dest.writeString(uid);
        dest.writeString(displayName);
        dest.writeString(email);
        dest.writeString(photoUrl);
        dest.writeString(clinicCode);
    }

    public Review(Parcel in) {
        rating = in.readFloat();
        feedbackText = in.readString();
        uid = in.readString();
        displayName = in.readString();
        email = in.readString();
        photoUrl = in.readString();
        clinicCode = in.readString();
    }

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
}

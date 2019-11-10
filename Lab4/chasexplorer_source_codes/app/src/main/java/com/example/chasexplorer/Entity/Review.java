package com.example.chasexplorer.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements  Parcelable {

    // Attributes for the Review Object
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

    /**
     * Standard constructor for Review Object
     * @param rating
     * @param feedbackText
     * @param uid
     * @param displayName
     * @param email
     * @param photoUrl
     * @param clinicCode
     */
    public Review(float rating, String feedbackText, String uid, String displayName, String email, String photoUrl, String clinicCode) {
        this.rating = rating;
        this.feedbackText = feedbackText;
        this.uid = uid;
        this.displayName = displayName;
        this.email = email;
        this.photoUrl = photoUrl;
        this.clinicCode = clinicCode;
    }

    /**
     * Getter method for Rating
     * @return rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * Setter method for Rating
     * @param rating
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * Getter method for FeedbackText
     * @return feedbackText
     */
    public String getFeedbackText() { return feedbackText; }

    /**
     * Setter method for FeedbackText
     * @param feedbackText
     */
    public void setFeedbackText(String feedbackText) { this.feedbackText = feedbackText; }

    /**
     * Getter method for UserId
     * @return Uid
     */
    public String getUid() { return uid; }

    /**
     * Setter method for UserId
     * @param uid
     */
    public void setUid(String uid) { this.uid = uid; }

    /**
     * Getter method for DisplayName
     * @return displayName
     */
    public String getDisplayName() { return displayName; }

    /**
     * Setter method for DisplayName
     * @param displayName
     */
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    /**
     * Getter method for Email
     * @return email
     */
    public String getEmail() { return email; }

    /**
     * Setter method for Email
     * @param email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Getter method for PhotoUrl
     * @return photoUrl
     */
    public String getPhotoUrl() { return photoUrl; }

    /**
     * Setter method for PhotoUrl
     * @param  photoUrl
     */
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    /**
     * Getter method for ClinicCode
     * @return clinicCode
     */
    public String getClinicCode(){ return clinicCode; }

    /**
     * Setter method for ClinicCode
     * @param clinicCode
     */
    public void setClinicCode(String clinicCode) { this.clinicCode = clinicCode; }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Called by Firebase internal methods to save data
     * @param dest
     * @param flags
     */
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

    /**
     * Clinic Constructor called by Firebase
     * @param in
     */
    public Review(Parcel in) {
        rating = in.readFloat();
        feedbackText = in.readString();
        uid = in.readString();
        displayName = in.readString();
        email = in.readString();
        photoUrl = in.readString();
        clinicCode = in.readString();
    }

    /**
     * Called by Firebase internal methods
     */
    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
}

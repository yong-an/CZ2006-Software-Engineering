package com.example.chasexplorer.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements  Parcelable {

    private float rating;
    private String feedbackText;
    private String imei;
    private String userId;

    public Review(){
        // Empty constructor for Firebase
    }

    public Review(float rating, String feedbackText, String imei, String userId) {
        this.rating = rating;
        this.feedbackText = feedbackText;
        this.imei = imei;
        this.userId = userId;
    }
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getFeedbackText() { return feedbackText; }

    public void setFeedbackText(String feedbackText) { this.feedbackText = feedbackText; }

    public String getImei() { return imei; }

    public void setImei(String imei) { this.imei = imei; }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

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
        dest.writeString(imei);
        dest.writeString(userId);
    }

    public Review(Parcel in) {
        rating = in.readFloat();
        feedbackText = in.readString();
        imei = in.readString();
        userId = in.readString();
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

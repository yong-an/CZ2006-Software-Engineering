package com.example.chasexplorer.Controller;

import android.util.Log;

import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.Entity.Review;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter {

    private static ArrayList<Review> reviewAl;

    public ReviewAdapter(){

    }

    public static void setReviewAl(ArrayList<Review> reviewArrayList){
        reviewAl = reviewArrayList;
    }

    public int saveReview(Clinic clinic, String feedback, float rating, String userId, String index, FirebaseDatabase fbDatabase, String imei){
        Review newReview = new Review();
        DatabaseReference myRef = fbDatabase.getReference().child(index);
        if (userId == null) {
            userId = "null";
        }
        try {
            Review r = new Review(rating, feedback, imei, userId, clinic.getPostalCode());
            myRef.child("reviewAl").child(userId).setValue(r);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public String getUsersFeedbackForClinic(String userId, int clinicPostalCode){
        if(reviewAl != null) {
            for (Review r : reviewAl) {
                if ((r.getUserId().equalsIgnoreCase(userId))&& r.getClinicPostalCode() == clinicPostalCode) {
                    return r.getFeedbackText();
                }
            }
        }
        return null;
    }

    public float getUsersRatingForClinic (String userId, int clinicPostalCode){
        if(reviewAl != null) {
            for (Review r : reviewAl) {
                if (r.getUserId().equalsIgnoreCase(userId) && r.getClinicPostalCode() == clinicPostalCode) {
                    return r.getRating();
                }
            }
        }
        return 0;
    }

    public float getAvgRatingForClinic(int clinicPostalCode){
        int size = 0;
        float rating = 0;
        if(reviewAl != null){
            for(Review r: reviewAl){
                if(r.getClinicPostalCode() == clinicPostalCode){
                    rating += r.getRating();
                    size++;
                }
            }
        }
        return ((float) rating/size);
    }

    public ArrayList<Review> getAllFeedbackForClinic(int clinicPostalCode){
        ArrayList<Review> newReviewAl = new ArrayList<Review>();
        if(reviewAl != null) {
            for (Review r : reviewAl) {
                if (r.getClinicPostalCode() == clinicPostalCode){
                    newReviewAl.add(r);
                }
            }
        }
        return newReviewAl;
    }

}

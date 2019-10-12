package com.example.chasexplorer.Controller;

import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.Entity.Review;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter {

    private static ArrayList<Review> reviewAl;

    public ReviewAdapter(){
        reviewAl = new ArrayList<Review>();
        for (Clinic c : ClinicAdapter.passMeAllData()){
            if (c.getReviewAl() != null) {
                reviewAl.addAll(c.getReviewAl());
            }
        }
    }

    public int saveReview(Clinic clinic, String feedback, float rating, String userId, String index, FirebaseDatabase fbDatabase, String imei){
        Review newReview = new Review();
        DatabaseReference myRef = fbDatabase.getReference().child(index);
        if (userId == null) {
            userId = " ";
        }
        Review r = new Review (rating,feedback,imei,userId);
        List<Review> rArraylist = new ArrayList<Review>();
        if(clinic.getReviewAl() != null)
            rArraylist.addAll(clinic.getReviewAl());
        rArraylist.add(r);
        myRef.child("reviewAl").push().setValue(rArraylist);
        return 1;
    }

    public String getUsersFeedbackForClinic(String userId, ArrayList<Review> reviewArray){
        if(reviewArray != null) {
            for (Review r : reviewArray) {
                if (r.getUserId() == userId) {
                    return r.getFeedbackText();
                }
            }
        }
        return " ";
    }

    public float getUsersRatingForClinic (String userId, ArrayList<Review> reviewArray){
        if(reviewArray != null) {
            Log.d(TAG, "its not null woohooo");
            for (Review r : reviewArray) {
                if (r.getUserId() == userId) {
                    return r.getRating();
                }
            }
        }
        return 0;
    }

}

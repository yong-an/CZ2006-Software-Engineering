package com.example.chasexplorer.Controller;


import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.Entity.Review;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReviewAdapter {

    private static ArrayList<Review> reviewAl;

    public ReviewAdapter(){

    }

    public static void setReviewAl(ArrayList<Review> reviewArrayList){
        reviewAl = reviewArrayList;
    }

    public boolean saveReview(Clinic clinic, String feedback, float rating, String uid, String email,String photoUrl, String index, FirebaseDatabase fbDatabase, String displayName){
        return false;
    }

    public String getUsersFeedbackForClinic(String Uid, String clinicCode){
        return null;
    }

    public float getUsersRatingForClinic (String Uid, String clinicCode){
        return 0;
    }

    public float getAvgRatingForClinic(String clinicCode){
        return 2.356f;
    }

    public ArrayList<Review> getAllFeedbackForClinic(String clinicCode){
        ArrayList<Review> newReviewAl = new ArrayList<Review>();
        if(reviewAl != null) {
            for (Review r : reviewAl) {
                if (r.getClinicCode().equalsIgnoreCase(clinicCode)){
                    newReviewAl.add(r);
                }
            }
        }
        return newReviewAl;
    }

    public int getNumberOfFeedbackForClinic(String clinicCode){
        int size = 0;
        return size;
    }

}

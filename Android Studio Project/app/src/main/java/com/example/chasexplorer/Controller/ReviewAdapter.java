package com.example.chasexplorer.Controller;


import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.Entity.Review;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReviewAdapter {

    private static ArrayList<Review> reviewAl;

    /**
     * Default Constructor
     */
    public ReviewAdapter(){

    }

    /**
     * set reviews
     * @param reviewArrayList
     */
    public static void setReviewAl(ArrayList<Review> reviewArrayList){
        reviewAl = reviewArrayList;
    }

    /**
     * This method will save the user reviews into the Firebase Database
     * @param clinic
     * @param feedback
     * @param rating
     * @param uid
     * @param email
     * @param photoUrl
     * @param index
     * @param fbDatabase
     * @param displayName
     */
    public boolean saveReview(Clinic clinic, String feedback, float rating, String uid, String email,String photoUrl, String index, FirebaseDatabase fbDatabase, String displayName){
        //Log.d(TAG, "What is index " + index);
        DatabaseReference myRef = fbDatabase.getReference().child(index);
        try {
            Review r = new Review(rating, feedback,uid, displayName, email, photoUrl, clinic.getClinicCode());
            myRef.child("reviewAl").child(uid).setValue(r);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method will retrieve an User's review on a particular clinic
     * @param Uid
     * @param clinicCode
     */
    public String getUsersFeedbackForClinic(String Uid, String clinicCode){
        if(reviewAl != null) {
            for (Review r : reviewAl) {
                if ((r.getUid().equalsIgnoreCase(Uid))&& r.getClinicCode().equalsIgnoreCase(clinicCode)) {
                    return r.getFeedbackText();
                }
            }
        }
        return null;
    }

    /**
     * This method will retrieve an User's rating on a particular clinic
     * @param Uid
     * @param clinicCode
     */
    public float getUsersRatingForClinic (String Uid, String clinicCode){
        if(reviewAl != null) {
            for (Review r : reviewAl) {
                if (r.getUid().equalsIgnoreCase(Uid) && r.getClinicCode().equalsIgnoreCase(clinicCode)) {
                    return r.getRating();
                }
            }
        }
        return 0;
    }

    /**
     * This method will compute the total average ratings of a clinic
     * @param clinicCode
     */
    public float getAvgRatingForClinic(String clinicCode){
        int size = 0;
        float rating = 0;
        if(reviewAl != null){
            for(Review r: reviewAl){
                if(r.getClinicCode().equalsIgnoreCase(clinicCode)){
                    rating += r.getRating();
                    size++;
                }
            }
        }
        return ((float) rating/size);
    }

    /**
     * This method will retrieve All reviews on a clinic
     * @param clinicCode
     */
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

    /**
     * This method will retrieve the total number of feedback for a particular clinic
     * Take note : This is not retrieving the rating scores
     * @param clinicCode
     */
    public int getNumberOfFeedbackForClinic(String clinicCode){
        int size = 0;

        if(reviewAl != null) {
            for (Review r : reviewAl) {
                if (r.getClinicCode().equalsIgnoreCase(clinicCode)){
                    size++;
                }
            }
        }
        return size;
    }

}

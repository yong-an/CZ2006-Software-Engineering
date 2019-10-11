package com.example.chasexplorer.Entity;

public class Review {

    private float rating;
    private String feedbackText;
    private String imei;
    private String userId;

    public Review(){

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

}

package com.example.chasexplorer.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chasexplorer.Boundary.ViewClinicDetailsActivity;
import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.Entity.Review;
import com.example.chasexplorer.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class ReviewRecyclableViewAdapter extends RecyclerView.Adapter<ReviewRecyclableViewAdapter.MyViewHolder> {
    private ArrayList<Review> mDataset;
    private Context mCtx;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView reviewView;
        public TextView usernameView;
        public ImageView dpView;

        public MyViewHolder(View v) {
            super(v);
            TextView feedbackView = (TextView) v.findViewById(R.id.feedbackView);
            TextView displayNameView = (TextView) v.findViewById(R.id.displayNameView);
            ImageView profilePicView = (ImageView) v.findViewById(R.id.profilePicView);
            reviewView = feedbackView;
            usernameView =  displayNameView;
            dpView = profilePicView;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReviewRecyclableViewAdapter(Context mCtx, ArrayList<Review> myDataset) {
        mCtx = mCtx;
        mDataset = myDataset;
    }

    public void setmDataset(ArrayList<Review> mDataset) {
        this.mDataset = mDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_listing, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Uri dpUrl = Uri.parse(mDataset.get(position).getPhotoUrl());
        holder.usernameView.setText(mDataset.get(position).getDisplayName());
        holder.reviewView.setText(mDataset.get(position).getFeedbackText());
        Picasso.get().load(dpUrl).error(R.drawable.chaslogo).into(holder.dpView);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
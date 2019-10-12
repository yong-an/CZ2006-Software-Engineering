package com.example.chasexplorer.Controller;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chasexplorer.Boundary.ViewClinicDetailsActivity;
import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.Entity.Review;
import com.example.chasexplorer.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class ReviewRecyclableViewAdapter extends RecyclerView.Adapter<ReviewRecyclableViewAdapter.MyViewHolder> {
    private ArrayList<Review> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;

        public MyViewHolder(View v) {
            super(v);
            TextView clinic = (TextView) v.findViewById(R.id.reviewsList);
            textView = clinic;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReviewRecyclableViewAdapter(ArrayList<Review> myDataset) {
        mDataset = myDataset;
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
        holder.textView.setText(mDataset.get(position).getUserId() + "\n" + mDataset.get(position).getFeedbackText());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
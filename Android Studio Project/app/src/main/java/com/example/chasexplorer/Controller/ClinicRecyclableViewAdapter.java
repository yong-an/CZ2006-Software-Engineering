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
import com.example.chasexplorer.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class ClinicRecyclableViewAdapter extends RecyclerView.Adapter<ClinicRecyclableViewAdapter.MyViewHolder> {
    private ArrayList<Clinic> mDataset;
    private ArrayList<Clinic> clinicList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;

        public MyViewHolder(View v) {
            super(v);
            TextView clinic = (TextView) v.findViewById(R.id.clinicName);
            textView = clinic;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ClinicRecyclableViewAdapter(ArrayList<Clinic> myDataset)  {
        clinicList = (ArrayList<Clinic>) myDataset.clone();;
        mDataset = myDataset;
    }

    public void setDataset(ArrayList<Clinic> myDataset){
        clinicList.clear();
        myDataset.clear();
        clinicList = (ArrayList<Clinic>) myDataset.clone();;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clinic_listing, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset.get(position).getClinicName()
                + "\n(+65)"  + mDataset.get(position).getClinicTelNo()
                + "\n" + mDataset.get(position).getStreetName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
//<<<<<<< HEAD
                Toast.makeText(r.getContext(),"Clicked View Detailed clinics Button", Toast.LENGTH_SHORT).show();
                //Toast.makeText(r.getContext(),, Toast.LENGTH_SHORT).show();
//=======
                //Toast.makeText(r.getContext(),"Clicked View Detailed clinics Button", Toast.LENGTH_SHORT).show();
//>>>>>>> 5c30ed5ac915e74aafb6ea52414af704e09d2669
                int index = mDataset.indexOf(mDataset.get(position));
                Intent i = new Intent(r.getContext(), ViewClinicDetailsActivity.class);
                i.putExtra("clinicObj", new Gson().toJson(mDataset.get(position)));
                i.putExtra("index" ,index);
                r.getContext().startActivity(i);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toUpperCase(Locale.getDefault());
        if (charText.length() == 0) {
            mDataset = (ArrayList<Clinic>) clinicList.clone();
            Log.d(TAG, "Adding clinicList to mDataset : " + mDataset.size());

        } else {
            mDataset.clear();
            for (Clinic c : clinicList) {
                if (c.getClinicName().toUpperCase(Locale.getDefault()).contains(charText)) {
                    mDataset.add(c);
                }
            }
        }
    }
}

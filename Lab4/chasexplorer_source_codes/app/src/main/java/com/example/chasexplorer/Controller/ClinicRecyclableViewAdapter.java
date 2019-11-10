package com.example.chasexplorer.Controller;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chasexplorer.Boundary.ViewClinicDetailsActivity;
import com.example.chasexplorer.Entity.Clinic;
import com.example.chasexplorer.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class ClinicRecyclableViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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

    public static class MyViewHolder2 extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView2;
        public TextView textView3;

        public MyViewHolder2(View v) {
            super(v);
            TextView clinic2 = (TextView) v.findViewById(R.id.clinicName2);
            TextView sectionHeader = (TextView) v.findViewById(R.id.sectionHeader);
            textView2 = clinic2;
            textView3 = sectionHeader;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ClinicRecyclableViewAdapter(ArrayList<Clinic> myDataset)  {
        clinicList = (ArrayList<Clinic>) myDataset.clone();
        mDataset = myDataset;
    }

    // Sets the Dataset for the Recyclable View Adapter to display the data in the newly created views
    public void setDataset(ArrayList<Clinic> myDataset){
        clinicList.clear();
        myDataset.clear();
        clinicList = (ArrayList<Clinic>) myDataset.clone();
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v;
        RecyclerView.ViewHolder vh = null;
        if(viewType == 0) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_listing_section_header, parent, false);
            vh = new MyViewHolder2(v);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_listing, parent, false);
            vh = new MyViewHolder(v);
        }
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final int position1 = position;
        if (holder instanceof MyViewHolder && (mDataset.get(position).toString2()!=null)) {
            MyViewHolder vh = (MyViewHolder) holder;
            vh.textView.setText(mDataset.get(position).toString2());
            vh.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View r) {
                    Intent i = new Intent(r.getContext(), ViewClinicDetailsActivity.class);
                    i.putExtra("clinicObj", new Gson().toJson(mDataset.get(position1)));
                    r.getContext().startActivity(i);
                }
            });
        } else if (holder instanceof MyViewHolder2) {
            MyViewHolder2 vh = (MyViewHolder2) holder;
            vh.textView2.setText(mDataset.get(position).toString2());
            vh.textView3.setText((String.valueOf(mDataset.get(position).getClinicName().charAt(0))));
            vh.textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View r) {
                    Intent i = new Intent(r.getContext(), ViewClinicDetailsActivity.class);
                    i.putExtra("clinicObj", new Gson().toJson(mDataset.get(position1)));
                    r.getContext().startActivity(i);
                }
            });
        }
    }

    /**
     * Returns 1 if the Clinic is the first Clinic that starts with a particular alphabet
     * Else, it returns 0
     */
    @Override
    public int getItemViewType(int position) {
        Character c;
        Character c2;
        if(position == 0)
            return 0;
        else
            c =  mDataset.get(position).getClinicName().charAt(0);
            c2 =  mDataset.get(position-1).getClinicName().charAt(0);
            if((c.equals(c2)))
                return 1;
            else
                return 0;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    /**
     *  Called by ViewClinicActivity to filter the Clinics based on the Clinic name provided by the user
     *  The results of Clinics which satisfy the search condition will be added to the ArrayList[Clinic]
     *  and it will reflected on the UI
     * @param charText
     */
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

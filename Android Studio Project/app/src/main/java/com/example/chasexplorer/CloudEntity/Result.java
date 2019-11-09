
package com.example.chasexplorer.CloudEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    // Attributes for the Result Object
    @SerializedName("opening_hours")
    @Expose
    private OpeningHours openingHours;

    /**
     * Getter method for OpeningHours
     * @return openingHour
     */
    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    /**
     * Setter method for OpeningHours
     * @param openingHours
     */
    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

}

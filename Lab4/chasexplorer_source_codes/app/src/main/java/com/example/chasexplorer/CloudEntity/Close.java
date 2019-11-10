
package com.example.chasexplorer.CloudEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Close {

    // Attributes for the Close Object
    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("time")
    @Expose
    private String time;

    /**
     * Getter method for Day
     * @return day
     */
    public Integer getDay() {
        return day;
    }

    /**
     * Setter method for Day
     * @param day
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * Getter method for Time
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     * Setter method for Time
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

}

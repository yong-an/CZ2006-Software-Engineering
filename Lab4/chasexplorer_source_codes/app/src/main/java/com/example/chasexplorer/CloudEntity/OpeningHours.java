
package com.example.chasexplorer.CloudEntity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpeningHours {

    // Attributes for the OpeningHours Object
    @SerializedName("open_now")
    @Expose
    private Boolean openNow;
    @SerializedName("periods")
    @Expose
    private List<Period> periods = null;
    @SerializedName("weekday_text")
    @Expose
    private List<String> weekdayText = null;

    /**
     * Getter method for OpenNow
     * @return openNow
     */
    public Boolean getOpenNow() {
        return openNow;
    }

    /**
     * Setter method for OpenNow
     * @param openNow
     */
    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

    /**
     * Getter method for Periods
     * @return periods
     */
    public List<Period> getPeriods() {
        return periods;
    }

    /**
     * Setter method for Periods
     * @param periods
     */
    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    /**
     * Getter method for WeekdayText
     * @return weekdayText
     */
    public List<String> getWeekdayText() {
        return weekdayText;
    }

    /**
     * Setter methods for WeekdayText
     * @param weekdayText
     */
    public void setWeekdayText(List<String> weekdayText) {
        this.weekdayText = weekdayText;
    }

}

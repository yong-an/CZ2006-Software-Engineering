
package com.example.chasexplorer.CloudEntity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceDetails {

    // Attributes for the PlaceDetails Object
    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = null;
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * Getter method for HtmlAttributions
     * @return htmlAttributions
     */
    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    /**
     * Setter method for HTMLAttributions
     * @param htmlAttributions
     */
    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    /**
     * Getter method for Result
     * @return result
     */
    public Result getResult() {
        return result;
    }

    /**
     * Setter method for Result
     * @param result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * Getter method for Status
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for Status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}

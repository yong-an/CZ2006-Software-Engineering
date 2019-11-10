
package com.example.chasexplorer.CloudEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Period {

    // Attributes for the Period Object
    @SerializedName("close")
    @Expose
    private Close close;
    @SerializedName("open")
    @Expose
    private Open open;

    /**
     * Getter method for Close
     * @return close
     */
    public Close getClose() {
        return close;
    }

    /**
     * Setter method for Close
     * @param close
     */
    public void setClose(Close close) {
        this.close = close;
    }

    /**
     * Getter method for Open
     * @return
     */
    public Open getOpen() {
        return open;
    }

    /**
     * Setter method for Open
     * @param open
     */
    public void setOpen(Open open) {
        this.open = open;
    }

}

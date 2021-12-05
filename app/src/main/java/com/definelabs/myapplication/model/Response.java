
package com.definelabs.myapplication.model;

import java.io.Serializable;
import java.util.List;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Response implements Serializable
{

    @SerializedName("venues")
    @Expose
    private List<Venue> venues = null;
    @SerializedName("confident")
    @Expose
    private Boolean confident;
    private final static long serialVersionUID = -7795857816830298385L;

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public Boolean getConfident() {
        return confident;
    }

    public void setConfident(Boolean confident) {
        this.confident = confident;
    }

}

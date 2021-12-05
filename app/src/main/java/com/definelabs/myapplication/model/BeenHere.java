
package com.definelabs.myapplication.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class BeenHere implements Serializable
{

    @SerializedName("lastCheckinExpiredAt")
    @Expose
    private Integer lastCheckinExpiredAt;
    private final static long serialVersionUID = -7253409680376096917L;
    public Integer getLastCheckinExpiredAt() {
        return lastCheckinExpiredAt;
    }
    public void setLastCheckinExpiredAt(Integer lastCheckinExpiredAt) {
        this.lastCheckinExpiredAt = lastCheckinExpiredAt;
    }

}

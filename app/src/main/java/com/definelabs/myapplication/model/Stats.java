
package com.definelabs.myapplication.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Stats implements Serializable
{

    @SerializedName("tipCount")
    @Expose
    private Integer tipCount;
    @SerializedName("usersCount")
    @Expose
    private Integer usersCount;
    @SerializedName("checkinsCount")
    @Expose
    private Integer checkinsCount;
    private final static long serialVersionUID = -8959012753846687084L;

    public Integer getTipCount() {
        return tipCount;
    }

    public void setTipCount(Integer tipCount) {
        this.tipCount = tipCount;
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public Integer getCheckinsCount() {
        return checkinsCount;
    }

    public void setCheckinsCount(Integer checkinsCount) {
        this.checkinsCount = checkinsCount;
    }

}

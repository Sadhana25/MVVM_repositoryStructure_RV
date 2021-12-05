
package com.definelabs.myapplication.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Item implements Serializable
{

    @SerializedName("unreadCount")
    @Expose
    private Integer unreadCount;
    private final static long serialVersionUID = 4722355503842177905L;

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

}


package com.definelabs.myapplication.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Notification implements Serializable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("item")
    @Expose
    private Item item;
    private final static long serialVersionUID = 2107661702657676149L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}

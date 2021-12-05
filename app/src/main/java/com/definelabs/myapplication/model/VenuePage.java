
package com.definelabs.myapplication.model;
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class VenuePage implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    private final static long serialVersionUID = -8610131747620323276L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

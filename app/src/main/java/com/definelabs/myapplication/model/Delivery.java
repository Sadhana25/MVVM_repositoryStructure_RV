
package com.definelabs.myapplication.model;

import java.io.Serializable;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Delivery implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("provider")
    @Expose
    private Provider provider;
    private final static long serialVersionUID = 2625516085018037338L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

}

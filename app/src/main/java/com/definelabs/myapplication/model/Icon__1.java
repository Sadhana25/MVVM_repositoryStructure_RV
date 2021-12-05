
package com.definelabs.myapplication.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Icon__1 implements Serializable
{

    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("sizes")
    @Expose
    private List<Integer> sizes = null;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = 5968735582376756390L;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<Integer> getSizes() {
        return sizes;
    }

    public void setSizes(List<Integer> sizes) {
        this.sizes = sizes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

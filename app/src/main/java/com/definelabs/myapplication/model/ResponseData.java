
package com.definelabs.myapplication.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseData implements Serializable
{

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("notifications")
    @Expose
    private List<Notification> notifications = null;
    @SerializedName("response")
    @Expose
    private Response response;
    private final static long serialVersionUID = -4769024036658826926L;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}

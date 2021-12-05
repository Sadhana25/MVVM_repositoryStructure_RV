package com.definelabs.myapplication.network;
import com.definelabs.myapplication.model.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface Api {
    String BASE_URL = "https://api.foursquare.com/v2/";
    @GET("venues/search?ll=40.7484,-73.9857&oauth_token=NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ&v=20180616")
    Call<ResponseData> getValues();

}
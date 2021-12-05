package com.definelabs.myapplication.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.definelabs.myapplication.database.AppDatabase;
import com.definelabs.myapplication.database.Match;
import com.definelabs.myapplication.database.MatchDao;
import com.definelabs.myapplication.model.ResponseData;
import com.definelabs.myapplication.model.Venue;
import com.definelabs.myapplication.network.Api;
import com.definelabs.myapplication.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SRepository {
    private static final String TAG = SRepository.class.getName();
    private static volatile SRepository INSTANCE;
    MatchDao matchDao;
    Api api;

    //fields to handle livedata
    private MutableLiveData<List<Venue>> addressListLiveData = new MutableLiveData<>();//Data from Api
    private LiveData<List<Match>> getAllMatch ;//data from Room Database
    private LiveData<Boolean> matchStatus;

    public SRepository(Context context) {
        api = RetrofitClient.getInstance().getMyApi();
        matchDao = AppDatabase.getInstance(context).matchDao();

    }
     // creating singletone
    public static SRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SRepository(context);
        }
        return INSTANCE;
    }

    //**********************network api call**************************************************

    public LiveData<List<Venue>> getAllMatches() {
        api.getValues().enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(@NonNull Call<ResponseData> call, @NonNull Response<ResponseData> response) {

                addressListLiveData.setValue(response.body().getResponse().getVenues());
                // Log.d(TAG, "onResponse: "+vlist.size());
            }

            @Override
            public void onFailure(@NonNull Call<ResponseData> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: failed to fetch todo list from server");
            }
        });
        return addressListLiveData;
    }

    //*********************Databse operation

    // get All database data
    public LiveData<List<Match>> getAllSaveMatch() {
        //casting to
        getAllMatch = matchDao.getAllMatch();
        return getAllMatch;
    }

    public void insertTask(Match match) {
        matchDao.insertMatch(match);
    }

    public void deleteTask(String match_id) {
        matchDao.deleteSaveMatch(match_id);
    }

    public void updateTask(Match match) {
        matchDao.insertMatch(match);
    }
//    public LiveData<Boolean> isDataExist(String match_id){
//        matchStatus=matchDao.dataExist(match_id);
//        return matchStatus;
//    }

}

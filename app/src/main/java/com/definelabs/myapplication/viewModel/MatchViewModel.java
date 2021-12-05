package com.definelabs.myapplication.viewModel;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.definelabs.myapplication.repository.SRepository;
import com.definelabs.myapplication.database.Match;
import com.definelabs.myapplication.model.Venue;
import java.util.List;
public class MatchViewModel extends AndroidViewModel {
    SRepository sRepository;
    //initaial progressbar status
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    // initially check the database
    private MutableLiveData<Boolean> isDataExistDb = new MutableLiveData<>();
    private LiveData<List<Venue>> allMatchList=new MutableLiveData<>();
    private LiveData<List<Match>> saveMatchList=new MutableLiveData<>();
    private LiveData<Boolean> saveMatchExist;

    public MatchViewModel(@NonNull Application application) {
        super(application);
        sRepository=new SRepository(application);
        allMatchList= sRepository.getAllMatches();
        saveMatchList= sRepository.getAllSaveMatch();
        isLoading.setValue(true);
        if(saveMatchList==null){
            isDataExistDb.postValue(false);
        }
        // saveMatchExist=
    }

    // this values use to bridge the ui
    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }
    public MutableLiveData<Boolean> isDataExistDb() {
        return isDataExistDb;
    }
    // all constructor methode******* //communicate repository to viewmodel
    public  LiveData<List<Venue>> getAllMatchList(){
        return allMatchList;
    }
    public LiveData<List<Match>> getSaveMatchList(){
        return saveMatchList;
    }

// // view to viewmodel to Repository
//    private LiveData<Boolean> getMatch_id(String matchh_id) {
//
//    }

    //communicate vm->repository
    public void insertMatchVM(Match match){
        sRepository.insertTask(match);
    }
    public  void deleteMatchVm(String match_id){
        sRepository.deleteTask(match_id);
    }
    public  void updateMatchVm(Match match){
        sRepository.updateTask(match);
    }



}

package com.definelabs.myapplication.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MatchDao {
    //insert update and delete
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMatch(Match match);
    @Delete
    void deleteMatch(Match match);
    @Update
    void updateMatch(Match match);
    @Query("SELECT * FROM match_table")
    LiveData<List<Match>> getAllMatch();

    @Query("DELETE FROM match_table")
    void deleteAll();
    //deletion Query
    @Query("DELETE FROM match_table WHERE match_id= :match_id ")
    void deleteSaveMatch(String match_id);

    @Query("SELECT EXISTS(SELECT * FROM match_table WHERE match_id = :match_id)")
     Boolean dataExist(String match_id);


}


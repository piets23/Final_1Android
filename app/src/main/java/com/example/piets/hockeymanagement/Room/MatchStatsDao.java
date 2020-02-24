package com.example.piets.hockeymanagement.Room;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MatchStatsDao
{

    @Insert
    public void addMatchstats(MatchStats matchStats);

    @Query("SELECT * FROM match_stats_database")
    List<MatchStats> getAll();

    //@Query("SELECT * FROM matchStats WHERE firstTeamTurnOver LIKE :firstTeamTurnOver LIMIT 1")
    //MatchStats findByName(String name);

   // @Insert
   // void insertAll(List<MatchStats> matchStats);

    @Update
    void update(MatchStats matchStats);

    @Delete
    void delete(MatchStats matchStats);

    @Query("SELECT * FROM match_stats_database WHERE statsPrimaryKey = :string")
    public List<MatchStats> findRecord(String string);

}

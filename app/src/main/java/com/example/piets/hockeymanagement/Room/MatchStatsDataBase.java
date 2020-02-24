package com.example.piets.hockeymanagement.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by piets on 2018/10/02.
 */

@Database(entities = {MatchStats.class}, version = 1 , exportSchema = false)
public abstract class MatchStatsDataBase extends RoomDatabase
{

    public abstract MatchStatsDao matchStatsDao();

    //Add this line not here but where you want to instantiate the room database , theres a room folder
    //where the room class is created all the data isnt in there only the data for the turnover fragment.

}



package com.example.piets.hockeymanagement.Classes;

import java.io.Serializable;

/**
 * Created by piets on 2018/08/14.
 */

public class Teams implements Serializable
{
    private String objectId;
    private String teamName, ageGroup, coach;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId( String objectId ) {
        this.objectId = objectId;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }


    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    @Override
    public String toString()
    {
        return teamName ;
    }
}

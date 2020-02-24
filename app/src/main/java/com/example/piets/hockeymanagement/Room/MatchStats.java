package com.example.piets.hockeymanagement.Room;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "match_stats_database")
public class MatchStats
{


    @NonNull
   @PrimaryKey
    private String statsPrimaryKey;

   @ColumnInfo(name = "firstTeamTurnOver")
    private int firstTeamTurnOver;

    @ColumnInfo(name = "firstVSTurnOver")
    private int firstVSTurnOver;

    @ColumnInfo(name = "secondTeamTurnOver")
    private int secondTeamTurnOver;

    @ColumnInfo(name = "secondVsTurnOver")
    private int secondVsTurnOver;

    @ColumnInfo(name = "firstTeamGoals")
    private int firstTeamGoals;

    @ColumnInfo(name = "firstVsGoals")
    private int firsVsGoals;

    @ColumnInfo(name = "secondTeamGoals")
    private int secondTeamGoals;

    @ColumnInfo(name = "secondVSGoals")
    private int secondVSGoals;

    //Circle penetrations data

    @ColumnInfo(name = "circlePenFH1")
    private int circlePenFH1;

    @ColumnInfo(name = "circlePenSH1")
    private int circlePenSH1;

    @ColumnInfo(name = "circlePenFH2")
    private int circlePenFH2;

    @ColumnInfo(name = "circlePenSH2")
    private int circlePenSH2;

    @ColumnInfo(name = "circlePenFH3")
    private int circlePenFH3;

    @ColumnInfo(name = "circlePenSH3")
    private int circlePenSH3;

    @ColumnInfo(name = "circlePenFH4")
    private int circlePenFH4;

    @ColumnInfo(name = "circlePenSH4")
    private int circlePenSH4;

    @ColumnInfo(name = "circlePenFH5")
    private int circlePenFH5;

    @ColumnInfo(name = "circlePenSH5")
    private int circlePenSH5;

    @ColumnInfo(name = "circlePenFHTotal")
    private int circlePenFHTotal;

    @ColumnInfo(name = "circlePenSHTotal")
    private int circlePenSHTotal;

    //penalty shots data

    @ColumnInfo(name = "penaltyShotsFH1")
    private int penaltyShotsFH1;

    @ColumnInfo(name = "penaltyShotsSH1")
    private int penaltyShotsSH1;

    @ColumnInfo(name = "penaltyShotsFH2")
    private int penaltyShotsFH2;

    @ColumnInfo(name = "penaltyShotsSH2")
    private int penaltyShotsSH2;

    @ColumnInfo(name = "penaltyShotsFH3")
    private int penaltyShotsFH3;

    @ColumnInfo(name = "penaltyShotsSH3")
    private int penaltyShotsSH3;

    @ColumnInfo(name = "penaltyShotsFH4")
    private int penaltyShotsFH4;

    @ColumnInfo(name = "penaltyShotsSH4")
    private int penaltyShotsSH4;

    @ColumnInfo(name = "penaltyShotsFH5")
    private int penaltyShotsFH5;

    @ColumnInfo(name = "penaltyShotsSH5")
    private int penaltyShotsSH5;

    @ColumnInfo(name = "penaltyShotsFHTotal")
    private int penaltyShotsFHTotal;

    @ColumnInfo(name = "penaltyShotsSHTotal")
    private int penaltyShotsSHTotal;

    //goal shots data
    @ColumnInfo(name = "goalShotsFH1")
    private int goalShotsFH1;

    @ColumnInfo(name = "goalShotsSH1")
    private int goalShotsSH1;

    @ColumnInfo(name = "goalShotsFH2")
    private int goalShotsFH2;

    @ColumnInfo(name = "goalShotsSH2")
    private int goalShotsSH2;

    @ColumnInfo(name = "goalShotsFH3")
    private int goalShotsFH3;

    @ColumnInfo(name = "goalShotsSH3")
    private int goalShotsSH3;

    @ColumnInfo(name = "goalShotsFH4")
    private int goalShotsFH4;

    @ColumnInfo(name = "goalShotsSH4")
    private int goalShotsSH4;

    @ColumnInfo(name = "goalShotsFH5")
    private int goalShotsFH5;

    @ColumnInfo(name = "goalShotsSH5")
    private int goalShotsSH5;

    @ColumnInfo(name = "goalShotsFHTotal")
    private int goalShotsFHTotal;

    @ColumnInfo(name = "goalShotsSHTotal")
    private int goalShotsSHTotal;


    //Getters and Setters

    public String getStatsPrimaryKey() {
        return statsPrimaryKey;
    }

    public void setStatsPrimaryKey(String statsPrimaryKey) {
        this.statsPrimaryKey = statsPrimaryKey;
    }

    public int getFirstTeamTurnOver() {
        return firstTeamTurnOver;
    }

    public void setFirstTeamTurnOver(int firstTeamTurnOver) {
        this.firstTeamTurnOver = firstTeamTurnOver;
    }

   public int getFirstVSTurnOver() {
        return firstVSTurnOver;
    }

    public void setFirstVSTurnOver(int firstVsTurnOver) {
        this.firstVSTurnOver = firstVsTurnOver;
    }

    public int getSecondTeamTurnOver() {
        return secondTeamTurnOver;
    }

    public void setSecondTeamTurnOver(int secondTeamTurnOver) {
        this.secondTeamTurnOver = secondTeamTurnOver;
    }

   public int getSecondVsTurnOver() {
        return secondVsTurnOver;
    }

    public void setSecondVsTurnOver(int secondVsTurnOver) {
        this.secondVsTurnOver = secondVsTurnOver;
    }

    public int getFirstTeamGoals() {
        return firstTeamGoals;
    }

    public void setFirstTeamGoals(int firstTeamGoals) {
        this.firstTeamGoals = firstTeamGoals;
    }

    public int getFirsVsGoals() {
        return firsVsGoals;
    }

    public void setFirsVsGoals(int firsVsGoals) {
        this.firsVsGoals = firsVsGoals;
    }

    public int getSecondTeamGoals() {
        return secondTeamGoals;
    }

    public void setSecondTeamGoals(int secondTeamGoals) {
        this.secondTeamGoals = secondTeamGoals;
    }

    public int getSecondVSGoals() {
        return secondVSGoals;
    }

    public void setSecondVSGoals(int secondVsGoals) {
        this.secondVSGoals = secondVsGoals;
    }

    //Circle penetrations data

    public int getCirclePenFH1() {
        return circlePenFH1;
    }

    public void setCirclePenFH1(int circlePenFH1) {
        this.circlePenFH1 = circlePenFH1;
    }

    public int getCirclePenSH1() {
        return circlePenSH1;
    }

    public void setCirclePenSH1(int circlePenSH1) {
        this.circlePenSH1 = circlePenSH1;
    }

    public int getCirclePenFH2() {
        return circlePenFH2;
    }

    public void setCirclePenFH2(int circlePenFH2) {
        this.circlePenFH2 = circlePenFH2;
    }

    public int getCirclePenSH2() {
        return circlePenSH2;
    }

    public void setCirclePenSH2(int circlePenSH2) {
        this.circlePenSH2 = circlePenSH2;
    }

    public int getCirclePenFH3() {
        return circlePenFH3;
    }

    public void setCirclePenFH3(int circlePenFH3) {
        this.circlePenFH3 = circlePenFH3;
    }

    public int getCirclePenSH3() {
        return circlePenSH3;
    }

    public void setCirclePenSH3(int circlePenSH3) {
        this.circlePenSH3 = circlePenSH3;
    }

    public int getCirclePenFH4() {
        return circlePenFH4;
    }

    public void setCirclePenFH4(int circlePenFH4) {
        this.circlePenFH4 = circlePenFH4;
    }

    public int getCirclePenSH4() {
        return circlePenSH4;
    }

    public void setCirclePenSH4(int circlePenSH4) {
        this.circlePenSH4 = circlePenSH4;
    }

    public int getCirclePenFH5() {
        return circlePenFH5;
    }

    public void setCirclePenFH5(int circlePenFH5) {
        this.circlePenFH5 = circlePenFH5;
    }

    public int getCirclePenSH5() {
        return circlePenSH5;
    }

    public void setCirclePenSH5(int circlePenSH5) {
        this.circlePenSH5 = circlePenSH5;
    }

    public int getCirclePenFHTotal() {
        return circlePenFHTotal;
    }

    public void setCirclePenFHTotal(int circlePenFHTotal) {
        this.circlePenFHTotal = circlePenFHTotal;
    }

    public int getCirclePenSHTotal() {
        return circlePenSHTotal;
    }

    public void setCirclePenSHTotal(int circlePenSHTotal) {
        this.circlePenSHTotal = circlePenSHTotal;
    }


    //penalty shots data
    public int getPenaltyShotsFH1() {
        return penaltyShotsFH1;
    }

    public void setPenaltyShotsFH1(int penaltyShotsFH1) {
        this.penaltyShotsFH1 = penaltyShotsFH1;
    }

    public int getPenaltyShotsSH1() {
        return penaltyShotsSH1;
    }

    public void setPenaltyShotsSH1(int penaltyShotsSH1) {
        this.penaltyShotsSH1 = penaltyShotsSH1;
    }

    public int getPenaltyShotsFH2() {
        return penaltyShotsFH2;
    }

    public void setPenaltyShotsFH2(int penaltyShotsFH2) {
        this.penaltyShotsFH2 = penaltyShotsFH2;
    }

    public int getPenaltyShotsSH2() {
        return penaltyShotsSH2;
    }

    public void setPenaltyShotsSH2(int penaltyShotsSH2) {
        this.penaltyShotsSH2 = penaltyShotsSH2;
    }

    public int getPenaltyShotsFH3() {
        return penaltyShotsFH3;
    }

    public void setPenaltyShotsFH3(int penaltyShotsFH3) {
        this.penaltyShotsFH3 = penaltyShotsFH3;
    }

    public int getPenaltyShotsSH3() {
        return penaltyShotsSH3;
    }

    public void setPenaltyShotsSH3(int penaltyShotsSH3) {
        this.penaltyShotsSH3 = penaltyShotsSH3;
    }

    public int getPenaltyShotsFH4() {
        return penaltyShotsFH4;
    }

    public void setPenaltyShotsFH4(int penaltyShotsFH4) {
        this.penaltyShotsFH4 = penaltyShotsFH4;
    }

    public int getPenaltyShotsSH4() {
        return penaltyShotsSH4;
    }

    public void setPenaltyShotsSH4(int penaltyShotsSH4) {
        this.penaltyShotsSH4 = penaltyShotsSH4;
    }

    public int getPenaltyShotsFH5() {
        return penaltyShotsFH5;
    }

    public void setPenaltyShotsFH5(int penaltyShotsFH5) {
        this.penaltyShotsFH5 = penaltyShotsFH5;
    }

    public int getPenaltyShotsSH5() {
        return penaltyShotsSH5;
    }

    public void setPenaltyShotsSH5(int penaltyShotsSH5) {
        this.penaltyShotsSH5 = penaltyShotsSH5;
    }

    public int getPenaltyShotsSHTotal() {
        return penaltyShotsSHTotal;
    }

    public void setPenaltyShotsSHTotal(int penaltyShotsSHTotal) {
        this.penaltyShotsSHTotal = penaltyShotsSHTotal;
    }

    public int getPenaltyShotsFHTotal() {
        return penaltyShotsFHTotal;
    }

    public void setPenaltyShotsFHTotal(int penaltyShotsFHTotal) {
        this.penaltyShotsFHTotal = penaltyShotsFHTotal;
    }


    public int getGoalShotsFH1() {
        return goalShotsFH1;
    }

    public void setGoalShotsFH1(int goalShotsFH1) {
        this.goalShotsFH1 = goalShotsFH1;
    }

    public int getGoalShotsSH1() {
        return goalShotsSH1;
    }

    public void setGoalShotsSH1(int goalShotsSH1) {
        this.goalShotsSH1 = goalShotsSH1;
    }

    public int getGoalShotsFH2() {
        return goalShotsFH2;
    }

    public void setGoalShotsFH2(int goalShotsFH2) {
        this.goalShotsFH2 = goalShotsFH2;
    }

    public int getGoalShotsSH2() {
        return goalShotsSH2;
    }

    public void setGoalShotsSH2(int goalShotsSH2) {
        this.goalShotsSH2 = goalShotsSH2;
    }

    public int getGoalShotsFH3() {
        return goalShotsFH3;
    }

    public void setGoalShotsFH3(int goalShotsFH3) {
        this.goalShotsFH3 = goalShotsFH3;
    }

    public int getGoalShotsSH3() {
        return goalShotsSH3;
    }

    public void setGoalShotsSH3(int goalShotsSH3) {
        this.goalShotsSH3 = goalShotsSH3;
    }

    public int getGoalShotsFH4() {
        return goalShotsFH4;
    }

    public void setGoalShotsFH4(int goalShotsFH4) {
        this.goalShotsFH4 = goalShotsFH4;
    }

    public int getGoalShotsSH4() {
        return goalShotsSH4;
    }

    public void setGoalShotsSH4(int goalShotsSH4) {
        this.goalShotsSH4 = goalShotsSH4;
    }

    public int getGoalShotsFH5() {
        return goalShotsFH5;
    }

    public void setGoalShotsFH5(int goalShotsFH5) {
        this.goalShotsFH5 = goalShotsFH5;
    }

    public int getGoalShotsSH5() {
        return goalShotsSH5;
    }

    public void setGoalShotsSH5(int goalShotsSH5) {
        this.goalShotsSH5 = goalShotsSH5;
    }

    public int getGoalShotsFHTotal() {
        return goalShotsFHTotal;
    }

    public void setGoalShotsFHTotal(int goalShotsFHTotal) {
        this.goalShotsFHTotal = goalShotsFHTotal;
    }

    public int getGoalShotsSHTotal() {
        return goalShotsSHTotal;
    }

    public void setGoalShotsSHTotal(int goalShotsSHTotal) {
        this.goalShotsSHTotal = goalShotsSHTotal;
    }
}

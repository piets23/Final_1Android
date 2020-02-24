package com.example.piets.hockeymanagement.Classes;

import java.io.Serializable;

/**
 * Created by piets on 2018/09/08.
 */

public class Matches implements Serializable
{
    private String team;
    private String opponent;
    private String leftForwardPlayer;
    private String rightForwardPlayer;
    private String centerForwardPlayer;
    private String leftLinkPlayer;
    private String rightLinkPlayer;
    private String centerLinkPlayer;
    private String leftBackPlayer;
    private String rightBackPlayer;
    private String centerBackPlayer;
    private String sweeperPlayer;
    private String goaliePlayer;
    private String benchOnePlayer;
    private String benchTwoPlayer;
    private String benchThreePlayer;
    private String benchFourPlayer;
    private String benchFivePlayer;
    private String objectId;
    private String numberOfMatch;

    private Float leftForwardRating, rightForwardRating, centerForwardRating, leftLinkRating, rightLinkRating,
    centerLinkRating, leftBackRating, rightBackRating, centerBackRating, sweeperRating, goalieRating,
    benchOneRating, benchTwoRating, benchThreeRating, benchFourRating, benchFiveRating;

    private Integer leftForwardGoals, rightForwardGoals, centerForwardGoals, leftLinkGoals, rightLinkGoals,
    centerLinkGoals, leftBackGoals, rightBackGoals, centerBackGoals, sweeperGoals, goalieGoals, benchOneGoals,
    benchTwoGoals, benchThreeGoals, benchFourGoals, benchFiveGoals;


    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getLeftForwardPlayer() {
        return leftForwardPlayer;
    }

    public void setLeftForwardPlayer(String leftForwardPlayer) {
        this.leftForwardPlayer = leftForwardPlayer;
    }

    public Integer getLeftForwardGoals() {
        return leftForwardGoals;
    }

    public void setLeftForwardGoals(Integer leftForwardGoals) {
        this.leftForwardGoals = leftForwardGoals;
    }

    public Float getLeftForwardRating() {
        return leftForwardRating;
    }

    public void setLeftForwardRating(Float leftForwardRating) {
        this.leftForwardRating = leftForwardRating;
    }

    public String getRightForwardPlayer() {
        return rightForwardPlayer;
    }

    public void setRightForwardPlayer(String rightForwardPlayer) {
        this.rightForwardPlayer = rightForwardPlayer;
    }

    public Float getRightForwardRating() {
        return rightForwardRating;
    }

    public void setRightForwardRating(Float rightForwardRating) {
        this.rightForwardRating = rightForwardRating;
    }

    public Integer getRightForwardGoals() {
        return rightForwardGoals;
    }

    public void setRightForwardGoals(Integer rightForwardGoals) {
        this.rightForwardGoals = rightForwardGoals;
    }

    public String getCenterForwardPlayer() {
        return centerForwardPlayer;
    }

    public void setCenterForwardPlayer(String centerForwardPlayer) {
        this.centerForwardPlayer = centerForwardPlayer;
    }

    public Float getCenterForwardRating() {
        return centerForwardRating;
    }

    public void setCenterForwardRating(Float centerForwardRating) {
        this.centerForwardRating = centerForwardRating;
    }

    public Integer getCenterForwardGoals() {
        return centerForwardGoals;
    }

    public void setCenterForwardGoals(Integer centerForwardGoals) {
        this.centerForwardGoals = centerForwardGoals;
    }

    public String getLeftLinkPlayer() {
        return leftLinkPlayer;
    }

    public void setLeftLinkPlayer(String leftLinkPlayer) {
        this.leftLinkPlayer = leftLinkPlayer;
    }

    public Float getLeftLinkRating() {
        return leftLinkRating;
    }

    public void setLeftLinkRating(Float leftLinkRating) {
        this.leftLinkRating = leftLinkRating;
    }

    public Integer getLeftLinkGoals() {
        return leftLinkGoals;
    }

    public void setLeftLinkGoals(Integer leftLinkGoals) {
        this.leftLinkGoals = leftLinkGoals;
    }

    public String getRightLinkPlayer() {
        return rightLinkPlayer;
    }

    public void setRightLinkPlayer(String rightLinkPlayer) {
        this.rightLinkPlayer = rightLinkPlayer;
    }

    public Float getRightLinkRating() {
        return rightLinkRating;
    }

    public void setRightLinkRating(Float rightLinkRating) {
        this.rightLinkRating = rightLinkRating;
    }

    public Integer getRightLinkGoals() {
        return rightLinkGoals;
    }

    public void setRightLinkGoals(Integer rightLinkGoals) {
        this.rightLinkGoals = rightLinkGoals;
    }

    public String getCenterLinkPlayer() {
        return centerLinkPlayer;
    }

    public void setCenterLinkPlayer(String centerLinkPlayer) {
        this.centerLinkPlayer = centerLinkPlayer;
    }

    public Float getCenterLinkRating() {
        return centerLinkRating;
    }

    public void setCenterLinkRating(Float centerLinkRating) {
        this.centerLinkRating = centerLinkRating;
    }

    public Integer getCenterLinkGoals() {
        return centerLinkGoals;
    }

    public void setCenterLinkGoals(Integer centerLinkGoals) {
        this.centerLinkGoals = centerLinkGoals;
    }

    public String getLeftBackPlayer() {
        return leftBackPlayer;
    }

    public void setLeftBackPlayer(String leftBackPlayer) {
        this.leftBackPlayer = leftBackPlayer;
    }

    public Float getLeftBackRating() {
        return leftBackRating;
    }

    public void setLeftBackRating(Float leftBackRating) {
        this.leftBackRating = leftBackRating;
    }

    public Integer getLeftBackGoals() {
        return leftBackGoals;
    }

    public void setLeftBackGoals(Integer leftBackGoals) {
        this.leftBackGoals = leftBackGoals;
    }

    public String getRightBackPlayer() {
        return rightBackPlayer;
    }

    public void setRightBackPlayer(String rightBackPlayer) {
        this.rightBackPlayer = rightBackPlayer;
    }

    public Float getRightBackRating() {
        return rightBackRating;
    }

    public void setRightBackRating(Float rightBackRating) {
        this.rightBackRating = rightBackRating;
    }

    public Integer getRightBackGoals() {
        return rightBackGoals;
    }

    public void setRightBackGoals(Integer rightBackGoals) {
        this.rightBackGoals = rightBackGoals;
    }

    public String getCenterBackPlayer() {
        return centerBackPlayer;
    }

    public void setCenterBackPlayer(String centerBackPlayer) {
        this.centerBackPlayer = centerBackPlayer;
    }

    public Float getCenterBackRating() {
        return centerBackRating;
    }

    public void setCenterBackRating(Float centerBackRating) {
        this.centerBackRating = centerBackRating;
    }

    public Integer getCenterBackGoals() {
        return centerBackGoals;
    }

    public void setCenterBackGoals(Integer centerBackGoals) {
        this.centerBackGoals = centerBackGoals;
    }

    public String getSweeperPlayer() {
        return sweeperPlayer;
    }

    public void setSweeperPlayer(String sweeperPlayer) {
        this.sweeperPlayer = sweeperPlayer;
    }

    public Float getSweeperRating() {
        return sweeperRating;
    }

    public void setSweeperRating(Float sweeperRating) {
        this.sweeperRating = sweeperRating;
    }

    public Integer getSweeperGoals() {
        return sweeperGoals;
    }

    public void setSweeperGoals(Integer sweeperGoals) {
        this.sweeperGoals = sweeperGoals;
    }

    public String getGoaliePlayer() {
        return goaliePlayer;
    }

    public void setGoaliePlayer(String goaliePlayer) {
        this.goaliePlayer = goaliePlayer;
    }

    public Float getGoalieRating() {
        return goalieRating;
    }

    public void setGoalieRating(Float goalieRating) {
        this.goalieRating = goalieRating;
    }

    public Integer getGoalieGoals() {
        return goalieGoals;
    }

    public void setGoalieGoals(Integer goalieGoals) {
        this.goalieGoals = goalieGoals;
    }

    public String getBenchOnePlayer() {
        return benchOnePlayer;
    }

    public void setBenchOnePlayer(String benchOnePlayer) {
        this.benchOnePlayer = benchOnePlayer;
    }

    public Float getBenchOneRating() {
        return benchOneRating;
    }

    public void setBenchOneRating(Float benchOneRating) {
        this.benchOneRating = benchOneRating;
    }

    public Integer getBenchOneGoals() {
        return benchOneGoals;
    }

    public void setBenchOneGoals(Integer benchOneGoals) {
        this.benchOneGoals = benchOneGoals;
    }

    public String getBenchTwoPlayer() {
        return benchTwoPlayer;
    }

    public void setBenchTwoPlayer(String benchTwoPlayer) {
        this.benchTwoPlayer = benchTwoPlayer;
    }

    public Float getBenchTwoRating() {
        return benchTwoRating;
    }

    public void setBenchTwoRating(Float benchTwoRating) {
        this.benchTwoRating = benchTwoRating;
    }

    public Integer getBenchTwoGoals() {
        return benchTwoGoals;
    }

    public void setBenchTwoGoals(Integer benchTwoGoals) {
        this.benchTwoGoals = benchTwoGoals;
    }

    public String getBenchThreePlayer() {
        return benchThreePlayer;
    }

    public void setBenchThreePlayer(String benchThreePlayer) {
        this.benchThreePlayer = benchThreePlayer;
    }

    public Float getBenchThreeRating() {
        return benchThreeRating;
    }

    public void setBenchThreeRating(Float benchThreeRating) {
        this.benchThreeRating = benchThreeRating;
    }

    public Integer getBenchThreeGoals() {
        return benchThreeGoals;
    }

    public void setBenchThreeGoals(Integer benchThreeGoals) {
        this.benchThreeGoals = benchThreeGoals;
    }

    public String getBenchFourPlayer() {
        return benchFourPlayer;
    }

    public void setBenchFourPlayer(String benchFourPlayer) {
        this.benchFourPlayer = benchFourPlayer;
    }

    public Float getBenchFourRating() {
        return benchFourRating;
    }

    public void setBenchFourRating(Float benchFourRating) {
        this.benchFourRating = benchFourRating;
    }

    public Integer getBenchFourGoals() {
        return benchFourGoals;
    }

    public void setBenchFourGoals(Integer benchFourGoals) {
        this.benchFourGoals = benchFourGoals;
    }

    public String getBenchFivePlayer() {
        return benchFivePlayer;
    }

    public void setBenchFivePlayer(String benchFivePlayer) {
        this.benchFivePlayer = benchFivePlayer;
    }

    public Float getBenchFiveRating() {
        return benchFiveRating;
    }

    public void setBenchFiveRating(Float benchFiveRating) {
        this.benchFiveRating = benchFiveRating;
    }

    public Integer getBenchFiveGoals() {
        return benchFiveGoals;
    }

    public void setBenchFiveGoals(Integer benchFiveGoals) {
        this.benchFiveGoals = benchFiveGoals;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getNumberOfMatch() {
        return numberOfMatch;
    }

    public void setNumberOfMatch(String numberOfMatch) {
        this.numberOfMatch = numberOfMatch;
    }
}

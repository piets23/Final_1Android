package com.example.piets.hockeymanagement.Classes;

import java.io.Serializable;

/**
 * Created by piets on 2018/08/14.
 */

public class Players implements Serializable
{
    private String objectId;
    private String name, surname, medAidName, medAidPlan, allergies, medAidNumber, parentNumber1, parentNumber2, team;

    public Players()
    {

    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMedAidName() {
        return medAidName;
    }

    public void setMedAidName(String medAidName) {
        this.medAidName = medAidName;
    }

    public String getMedAidPlan() {
        return medAidPlan;
    }

    public void setMedAidPlan(String medAidPlan) {
        this.medAidPlan = medAidPlan;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getMedAidNumber() {
        return medAidNumber;
    }

    public void setMedAidNumber(String medAidNumber) {
        this.medAidNumber = medAidNumber;
    }

    public String getParentNumber1() {
        return parentNumber1;
    }

    public void setParentNumber1(String parentNumber1) {
        this.parentNumber1 = parentNumber1;
    }

    public String getParentNumber2() {
        return parentNumber2;
    }


    public String toString()
    {
        return surname + "," + name;
    }

    public void setParentNumber2(String parentNumber2) {
        this.parentNumber2 = parentNumber2;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}

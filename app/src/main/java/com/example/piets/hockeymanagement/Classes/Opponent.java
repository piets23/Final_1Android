package com.example.piets.hockeymanagement.Classes;

/**
 * Created by piets on 2018/08/13.
 */

public class Opponent
{
    private String objectId;
    private String name;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId( String objectId ) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String toString()
    {
        return  name;
    }
}

package com.example.piets.hockeymanagement.Classes;

/**
 * Created by piets on 2018/07/22.
 */

public class Users
{
    private String objectId;
    private String email;
    private String name;
    private String surname;
    private String password;
    private String role;

    public String getObjectId() {
        return objectId;
    }
    public void setObjectId( String objectId ) {
        this.objectId = objectId;
    }

    public Users()
    {

    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString()
    {
        return surname + "," + name;
    }

}

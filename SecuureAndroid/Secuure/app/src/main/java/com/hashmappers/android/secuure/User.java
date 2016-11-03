package com.hashmappers.android.secuure;

import android.util.Log;

/**
 * Created by Jamie on 10/16/2016.
 *
 * Edit by sasong 10/17/2016
 * Added user methods
 */

public class User {

    // Private
    String name, username, password;

    // Protected

    // Public

    //Constructors
    // This is a function to create a new user
    public User(){
        name = "John Doe";
        username = "johndoe";
        password = "password";
    }

    public User (String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User (String username, String password) {
        this.username = username;
        this.password = password;
        this.name = username;
    }

    //Methods
    public String getUsername(){
        return username;
    }
    public String getName(){return name;}
    public String getPassword(){return password;}

    public Boolean checkPassword(String pass){
        if (password ==  pass)
            return true;
        else
            return false;
    }

    public void changePassword(String pass) {
        password = pass;
    }

    public void changeUsername(String userN){
        username = userN;
    }

    public void setUser(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    //used for anonymous users
    public void setANUser(String username, String password){
        this.username = username;
        this.password = password;

    }

    public void printUser(){
        Log.d("user", username + "\n" + password + "\n");
    }
}

package com.hashmappers.android.secuure;

/**
 * Created by voidtm on 10/15/16.
 * NOT IN USE
 */

public class Account {
    //Private data
    String username;
    String password;
    String appName;
    // Public
    // Constructor

    public Account(String userN, String pass, String appName) {
        username = userN;
        password = pass;
        this.appName = appName;
    }

    public Account(String userN, String pass) {
        username = userN;
        password = pass;
        appName = "(NONE)";
    }

    //Methods
    public String getUsername(){
        return username;
    }

    public Boolean checkPassword(String pass){
        if (password ==  pass)
            return true;
        else
            return false;
    }

    public Boolean checkUsername(String userN){
        if (username ==  userN)
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
}

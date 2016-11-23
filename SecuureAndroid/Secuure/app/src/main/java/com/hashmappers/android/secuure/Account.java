package com.hashmappers.android.secuure;

import android.util.Log;

import com.google.gson.JsonObject;

/**
 * Created by voidtm on 10/15/16.
 * NOT IN USE
 */

public class Account {
    //Private data
    String username;
    String password;
    String appName;
    String note;
    // Public
    // Constructor

    public Account() {
        username = "";
        password = "";
        appName = "";
        note =  "";
    }
    public Account(String userN, String pass, String appName, String note) {
        username = userN;
        password = pass;
        this.appName = appName;
        this.note = note;
    }

    public Account(String userN, String pass) {
        username = userN;
        password = pass;
        appName = "";
        note =  "";
    }

    public Account(Account copy){
        this.username = copy.username;
        this.password = copy.password;
        this.appName = copy.appName;
        this.note = copy.note;
    }

    public Account(JsonObject obj){
        username = obj.get("account").toString().replaceAll("^\"|\"$", "");
        //Log.w("username", username);
        password = obj.get("password").toString().replaceAll("^\"|\"$", "");
        //Log.w("password", password);
        note = obj.get("notes").toString().replaceAll("^\"|\"$", "");
        //Log.w("note", note);
        appName = obj.get("website").toString().replaceAll("^\"|\"$", "");
        //Log.w("appName", appName);
    }

    //Methods
    public String getUsername(){
        return username;
    }

    public String getAppName() { return appName; }

    public String getNote() { return note; }

    public String getPassword() { return password; }

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

    public void set(Account copy){
        this.username = copy.username;
        this.password = copy.password;
        this.appName = copy.appName;
        this.note = copy.note;
    }

    public void changePassword(String pass) {
        password = pass;
    }

    public void changeUsername(String userN){
        username = userN;
    }

    public void changeNote(String note){ this.note = note; }

    public void changeApp(String appName){ this.appName = appName; }
}

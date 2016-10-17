package com.hashmappers.android.secuure;

import android.content.SharedPreferences;
import android.content.Context;

/**
 * Created by Jamie on 10/16/2016.
 */

// This class stores user data on the phone
public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    // This method should store the following data for a new user
    public void storedUserData (User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.commit();
    }

    // This method gets the data of the user who is logged on and should return a user
    public User getLoggedInUser () {
        String name = userLocalDatabase.getString("name", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");

        User storedUser = new User(name, username, password);
        return storedUser;
    }

    // If the user is logged in
    public void setUserLoggedIn (boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn",loggedIn);
        spEditor.commit();
    }

    // This method gets if a user is logged in or not
    public boolean getUserLoggedIn() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == true) {
            return true;
        } else {
            return false;
        }
    }

    //Clear all the user data when he/she is logging out
    public void clearUserData () {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}

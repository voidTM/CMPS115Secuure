package com.hashmappers.android.secuure;

/**
 * Created by Jamie on 10/16/2016.
 */

public class User {

    String name, username, password;

    // This is a function to create a new user
    public User (String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User (String username, String password) {
        this.username = username;
        this.password = password;
        this.name = "";
    }
}

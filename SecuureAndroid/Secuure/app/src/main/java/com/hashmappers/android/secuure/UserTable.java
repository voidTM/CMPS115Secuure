package com.hashmappers.android.secuure;

/**
 * Created by voidtm on 10/18/16.
 * Should only need to be initialized once
 */


import java.util.HashMap;

/**
 * Created by voidtm on 10/17/16.
 * Should be populated at the beginning or end
 */

public class UserTable{
    // Private
    private  HashMap<String, User> accounts; //table is only initialized once
    //private static UserTable instance = null;


    //Public
    // Constructor
    public UserTable()
    {
        //initialize blank hashmap
        accounts = new HashMap<String, User>();
    }

    // initialize accounttable from storage


    // Methods
    public void addUser(User newUser){
        String usrname = newUser.getUsername();
        accounts.put(usrname, newUser);
    }

    public Boolean delUser(String usrname){

        if( accounts.containsKey(usrname) == true) {
            accounts.remove(usrname);
            return true;
        }
        else
            return false;
    }

    public User getUser(String usrname){
        return accounts.get(usrname);
    }
}

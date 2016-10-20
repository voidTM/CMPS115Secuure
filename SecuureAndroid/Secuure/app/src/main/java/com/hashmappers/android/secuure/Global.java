package com.hashmappers.android.secuure;

/**
 * Created by voidtm on 10/18/16.
 * Class for storing holding global variables
 */

public class Global {
    private static AccountTable accounts = null;
    private static User usr = null;
    private static UserTable userT = null;

    public static synchronized AccountTable getAccountT()
    {
        if(accounts == null)
            accounts = new AccountTable();
        return accounts;
    }

    public static synchronized User getUser()
    {
        if(usr == null)
            usr = new User();
        return usr;
    }

    public static synchronized UserTable getUserT(){
        if(userT == null)
            userT = new UserTable();
        return userT;
    }

    public static synchronized void reset()
    {
        accounts = new AccountTable();
        usr = new User();
        userT = new UserTable();
    }
}
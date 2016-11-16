package com.hashmappers.android.secuure;

/**
 * Created by voidtm on 10/18/16.
 * Class for storing holding global variables
 */

public class Global {
    private static AccountTable accounts = null; //may not use?
    private static User usr = null; // Should remain static throughout
    private static Account acc = null;

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

    public static synchronized Account getAcc(){
        if(acc == null)
            acc = new Account();
        return acc;
    }

    public static synchronized void reset()
    {
        accounts = new AccountTable();
        usr = new User();
        acc = new Account();
    }

    public static synchronized void resetAccount(){
        acc = new Account();
    }


}
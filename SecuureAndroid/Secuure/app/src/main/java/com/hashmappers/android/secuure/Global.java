package com.hashmappers.android.secuure;

/**
 * Created by voidtm on 10/18/16.
 * Class for storing holding global variables
 */

/**
 * Global are a group of global variables that are required throughout the program.
 *
 * */
public class Global {
    private static AccountTable accounts = null;
    private static User usr = null; // Initialized after login
    private static Account acc = null;
    private static String passGenValue = null;

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

    public static synchronized String getPassGenValue(){
        if(passGenValue == null){
            passGenValue = new String("");
        }

        return passGenValue;
    }
    // resets and clears all values upon logging out
    public static synchronized void reset()
    {
        accounts = new AccountTable();
        usr = new User();
        acc = new Account();
        passGenValue = new String("");
    }

    // Resets value of the account as needed.
    public static synchronized void resetAccount(){
        acc = new Account();
    }

    public static synchronized void resetPassGenValue(){
        passGenValue = new String("");
    }
}
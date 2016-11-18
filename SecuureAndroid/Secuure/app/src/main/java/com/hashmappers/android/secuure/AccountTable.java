package com.hashmappers.android.secuure;

import com.google.gson.JsonObject;

import java.util.HashMap;

/**
 * Created by voidtm on 10/17/16.
 * Should be populated at the beginning or end
 * NOT IN USE
 */

 public class AccountTable{
    // Private
	private Integer counter;
    private HashMap<String, String> website;
    private HashMap<String, Account> accounts;

    //Public

    // Constructor
    public AccountTable()
    {
        //initialize blank hashmap
        counter = 0;
        accounts = new HashMap<String, Account>();
    }

    // initialize accounttable from storage


    // Methods
    public void addAcount(Account newAccount){
        String usrname = newAccount.getUsername();
        accounts.put(usrname, newAccount);
    }

    public Boolean delAccount(String usrname){
        return accounts.containsKey(usrname);
    }

    public Account getAccount(String usrname){
        return accounts.get(usrname);
    }
}

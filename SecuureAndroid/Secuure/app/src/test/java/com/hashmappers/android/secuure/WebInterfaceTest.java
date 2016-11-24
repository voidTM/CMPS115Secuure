package com.hashmappers.android.secuure;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import org.junit.Test;

import java.sql.DriverManager;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

/**
 * Created by voidtm on 11/23/16.
 * For unit test cases
 */

@RunWith(MockitoJUnitRunner.class)

public class WebInterfaceTest {

    WebInterface web = WebService.getService();

    // Data set that will be used for the unit test
    String username = "username";
    String userpwd = "password";
    String accname = "vainvoid";
    String accpwd = "awesome";
    String note = "Pinaccle of Awesome";
    String title = "Vainglory";


    @Test
    public void registerUser() throws Exception {

        Call<String> call = web.registerUser(username, userpwd, "John", "Doe");
        System.out.print("Status before register usr call");

        call.enqueue(new Callback<String>() {
            @Override
            // check for any messages
            public void onResponse(Call<String> call, Response<String> response) {
                int statusCode = response.code();
                //Boolean sucess //= response.body();
                System.out.print("Apicall" + "Status " + statusCode);
                if(statusCode == 200)
                    Log.w("Result", "" +response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.print("Apicall" + "error:" + t.getMessage());
            }
        });

    }

    @Test
    public void login() throws Exception {

        Call<JsonObject> call = web.login(username, userpwd);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                int statusCode = response.code();
                JsonObject output = response.body();
                Log.w("Apicall", "Status " + statusCode);
                System.out.print("Status " + statusCode);
                if(statusCode == 200)
                    Log.w ("Result", output.getAsString());

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Log error here since request failed
                Log.w("Apicall", "error:" + t.getMessage());
            }
        });

    }

    @Test
    public void addAccount() throws Exception {
        Call<String> call = web.addAccount(username, userpwd, accname, title, accpwd, note);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int statusCode = response.code();
                Log.w("Apicall", "Status " + statusCode);
                if(statusCode == 200)
                    Log.w("Results", response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Log error here since request failed
                Log.e("Apicall", "error:" + t.getMessage());
            }
        });

    }

    @Test
    public void getAllAccount() throws Exception {

        Call<JsonArray> call = web.getAllAccounts(username, userpwd);;
        // Performs a call to the server requesting for the accounts
        // the user has stored.
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                int statusCode = response.code();
                Log.w("Apicall", "Status " + statusCode);
                if(statusCode == 200)
                    Log.w("Results", response.body().toString());
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                // Log error here since request failed
                Log.e("Apicall", "error:" + t.getMessage());
            }
        });

    }

    @Test
    public void editAccount() throws Exception {
        Call<String> call = web.editAccount(username, userpwd, accname, title, "new password", "Hello");


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int statusCode = response.code();
                Log.w("Apicall", "Status " + statusCode);
                if(statusCode == 200)
                    Log.w("Results", response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Log error here since request failed
                Log.e("Apicall", "error:" + t.getMessage());
            }
        });

    }


    @Test
    public void deleteAccount() throws Exception {

        Call<String> call = web.deleteAccount(username, userpwd, accname, title);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int statusCode = response.code();
                Log.w("Apicall", "Status " + statusCode);
                // Debug message from server.
                if(statusCode == 200)
                    Log.w("Response", response.body());
                // if statusCode 200  start activity?
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Log error here since request failed
                Log.e("Apicall", "error:" + t.getMessage());
            }
        });

    }


}
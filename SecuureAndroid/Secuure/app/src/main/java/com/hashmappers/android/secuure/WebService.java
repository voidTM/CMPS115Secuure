package com.hashmappers.android.secuure;


/**
 * Created by voidtm on 10/20/16.
 * non functional purpose
 * Based off of http://wiki.workassis.com/android-retrofit-2-1-http-client/
 */


import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class WebService {
    // Attempt to access the web
    //public static String BASE_URL = "http://98.234.141.183:8080/"; // server1 ip
    //public static String BASE_URL = "http://" server2 ip
    public static String BASE_URL = "http://10.0.2.2/";
    private static WebInterface service;

    /**
     * @return
     */
    public WebService(){
        if(service == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            Retrofit rest = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonStringConverterFactory.create(gson))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            ;

            service = rest.create(WebInterface.class);
        }
    }

    public static WebInterface getService() {

        if(service == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            Retrofit rest = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonStringConverterFactory.create(gson))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            ;

            service = rest.create(WebInterface.class);
        }

        return service;
    }

    public void resetService(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit rest = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ;

        service = rest.create(WebInterface.class);
    }

    public void ifInternet(Boolean internet) {
        if(internet)
            BASE_URL = "http://10.0.2.2/";
        else
            BASE_URL = "http://localhost/";
    }


}

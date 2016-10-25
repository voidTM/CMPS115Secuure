package com.hashmappers.android.secuure;

/**
 * Created by voidtm on 10/20/16.
 */

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface WebInterface {

    // Gets list of all users prb not used
    @GET("get/users")
    Call<List<User>> getAllUsers(); //List<User> is the response from api

    @GET("get/accounts")
    Call<List<Account>> getAllAccounts(User user); //Gets all the accounts For a particular user


    @GET("get/user")    
    Call<User> getUser( @Query("id") int id); //using query param (get/user?id=17479666). User is the response from the api

    //@GET("get/user/{id}")
    //Call<User> getUser( @Query("id") int id); //using path param (get/user/17479666)

    @POST("register/user")
    Call<User> registerUser(@Body User user);

    @POST("update/{id}/user")
    Call<User> updateUser(@Field("name") String name, @Path("id") String id);

    //Call the server to check user identification
    @POST("compare/user")
    Call<Boolean> identifyUser(@Body User user);

}

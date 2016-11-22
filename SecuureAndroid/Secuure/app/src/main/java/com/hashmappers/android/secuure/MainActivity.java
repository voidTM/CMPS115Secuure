package com.hashmappers.android.secuure;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button bLogin, bSignUp;
    EditText etUsername, etPassword;
    private RelativeLayout relativeLayout;
    WebInterface web;
    String username, password;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    // UserLocalStore userLocalStore;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        web = WebService.getService();
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        bSignUp = (Button) findViewById(R.id.bSignUp);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            bLogin.setOnClickListener(this);
            bSignUp.setOnClickListener(this);
        } else {
            Toast pass = Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_SHORT);
            pass.show();
        }

        // userLocalStore = new UserLocalStore(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin:
                // Converts username and password to string
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                WebInterface web = WebService.getService();
                // calls login
                login(username, password);
                break;
            case R.id.bSignUp:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    // function to log the user in
    public void login(String username, String password) {

        // Checks to make sure that user has inputed a username and password
        if ((!username.isEmpty() && username.length() > 0) && (!password.isEmpty() && password.length() > 0)) {
            Call<JsonObject> call = web.login(username, password);
            Response<JsonObject> respond;
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    int statusCode = response.code();
                    JsonObject output = response.body();
                    boolean result = output.get("login").getAsBoolean();
                    //Log.w("Apicall", result);
                    Log.w("Apicall", "Status " + statusCode);

                    switchToLogin(result);
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    // Log error here since request failed
                    Log.w("Apicall", "" + t.getMessage());
                    //Send error status here too?
                    //AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    //builder.setMessage("Error: Server Request Failed")
                     //       .setPositiveButton("OK", null);
                    //AlertDialog alert = builder.create();
                    //alert.show();
                    switchToLogin(false);
                }
            });
        }

    }

    // Checks to see if user logged successfully
    // if so switch to next stage
    void switchToLogin(boolean loggedIn) {
        if (loggedIn) { //Login successfull

            Toast pass = Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT);
            User usr = Global.getUser();
            usr.setANUser(username, password);
            // check login for users?
            pass.show();
            // Move to next activity
            startActivity(new Intent(this, Login.class));
        } else {
            // Display the popup window in the center of screen if you fail to log in correctly
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setMessage("Error: Invalid Log In")
                    .setPositiveButton("OK", null);
            AlertDialog alert = builder.create();
            alert.show();
        }
            /* AlertDialog OptionDialog = new AlertDialog.Builder(this).create();
    * background.setOnClickListener(new OnclickListener() {
    *       public void onClick(View v) {
    *           SetBackground();
    *           OptionDialog.dismiss();
    *       }
    * }); */
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    public Action getIndexApiAction() {
//        Thing object = new Thing.Builder()
//                .setName("Main Page") // TODO: Define a title for the content shown.
//                // TODO: Make sure this auto-generated URL is correct.
//                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
//                .build();
//        return new Action.Builder(Action.TYPE_VIEW)
//                .setObject(object)
//                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
//                .build();
//    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        AppIndex.AppIndexApi.start(client, getIndexApiAction());
//    }

//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        AppIndex.AppIndexApi.end(client, getIndexApiAction());
//        client.disconnect();
//    }

// public boolean checkConnectivity() {
    // boolean connected = false;
    // ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    // if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
    // connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
    // connected = true;
    // } else {
    // connected = false;
    // }
//}

}
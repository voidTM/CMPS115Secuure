package com.unittest.android.phpunittest;

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
import android.widget.LinearLayout;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    WebPhpTest test;
    LinearLayout base;
    Button register ,login ,read ,edit, delete, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test = new WebPhpTest();
        setContentView(R.layout.activity_main);
        base = (LinearLayout) findViewById(R.id.Home);
        register = (Button) findViewById(R.id.Register);
        login = (Button) findViewById(R.id.Login);
        read = (Button) findViewById(R.id.Read);
        edit = (Button) findViewById(R.id.Edit);
        delete = (Button) findViewById(R.id.Delete);
        add = (Button) findViewById(R.id.Add);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        register.setOnClickListener(this);
        login.setOnClickListener(this);
        read.setOnClickListener(this);
        edit.setOnClickListener(this);
        delete.setOnClickListener(this);
        add.setOnClickListener(this);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        Log.w("Test", "Clicked");
        switch (v.getId()) {
            case R.id.Register:
                try {
                    Log.w("Test", "Clicked");
                    test.registerUser();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.Login:
                try {
                    test.login();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.Read:
                try {
                    test.getAllAccount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.Edit:
                try {
                    test.editAccount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.Add:
                try {
                    test.addAccount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.Delete:
                try {
                    test.deleteAccount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

}

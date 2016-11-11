package com.hashmappers.android.secuure;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton addAccounts;
    Button bLogout;
    ListView list;

    String name;
    String accountEntry;
    TextView userWelcome;
    //UserLocalStore userLocalStore;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private CoordinatorLayout lView;
    private TableLayout tLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bLogout = (Button) findViewById(R.id.bLogout);
        addAccounts = (FloatingActionButton)findViewById(R.id.addAccounts);
        bLogout.setOnClickListener(this);
        addAccounts.setOnClickListener(this);
        userWelcome = (TextView) findViewById(R.id.textUserID);
        tLayout = (TableLayout) findViewById(R.id.titleList);
        User usr = Global.getUser();
        UserTable userT = Global.getUserT();
        name = usr.getName();

        userWelcome.setText("Welcome ");
        //userLocalStore = new UserLocalStore(this);

        lView = (CoordinatorLayout) findViewById(R.id.lLogin);
        TextView entry = new TextView(this);
        String password = usr.getPassword();
        String username = usr.getUsername();
        WebInterface web = WebService.getService();
        Log.w("Apicall", "User " + username);
        Log.w("Apicall", "Password " + password);

        Call<JsonArray> call = web.getAllAccounts(username, password);
        // Performs a call to the server requesting for the accounts
        // the user has stored.
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                int statusCode = response.code();
                Log.w("Apicall", "Status " + statusCode);
                if(statusCode == 200)
                    updateAccountTable(response.body());
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                // Log error here since request failed
                Log.e("Apicall", t.getMessage());
            }
        });
    }

/*    @Override
    protected void onStart() {
        super.onStart();
    }

    // This method is to check if a user is actually logged in
    private boolean authenticate () {
        return userLocalStore.getUserLoggedIn();
    }*/


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogout:
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);

                builder.setMessage("Are you sure you want to log out?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Global.reset();
                                startActivity(new Intent(Login.this, MainActivity.class));
                            }
                        })
                        .setNegativeButton("NO", null);
                AlertDialog alert = builder.create();
                alert.show();
/*              userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);*/
/*                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popuplogout, null);

                // Pressing the log out button, a popup window asks if you want to log out
                popupWindow = new PopupWindow(container, 600, 300, true);
                popupWindow.showAtLocation(lView, Gravity.CENTER, 0, 0);

                // If you do not want to sign out just close the popup
                Button bNo = (Button) container.findViewById(R.id.bNo);
                bNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                // If you do want to sign out, this should take you back to the log in screen
                Button bYes = (Button) container.findViewById(R.id.bYes);
                bYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }
                });*/
                break;
            case R.id.addAccounts:
                startActivity(new Intent(this, AddingAccounts.class));
                break;

        }
    }

    // This method will take in a Json response from
    // server and then populate the current table.
    public void updateAccountTable(JsonArray array){

        // id format will row number * 10 + fieldnumber
        for(int i = 0; i < array.size(); i++){
            int id = i * 10;
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            row.setId(id);
            JsonObject acc = array.get(i).getAsJsonObject();
            Log.w("Apicall", "Object: " + acc.toString());

            TextView nameField = new TextView(this);
            nameField.setId(id+1);
            nameField.setText(acc.get("website").toString());
            row.addView(nameField);

            TextView accnameField = new TextView(this);
            accnameField.setId(id+2);
            accnameField.setText(acc.get("account").toString());
            row.addView(accnameField);

            tLayout.addView(row);
        }

    }
}
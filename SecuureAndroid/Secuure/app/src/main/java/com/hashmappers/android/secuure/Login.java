
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

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton addAccounts;
    Button bLogout;
    HashMap<Integer, Account> accountTable;

    String name;
    String accountEntry;
    TextView userWelcome;
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
        tLayout = (TableLayout) findViewById(R.id.titleList);

        bLogout.setOnClickListener(this);
        addAccounts.setOnClickListener(this);
        userWelcome = (TextView) findViewById(R.id.textUserID);
        User usr = Global.getUser();
        name = usr.getName();

        //userLocalStore = new UserLocalStore(this);
        accountTable = new HashMap<Integer, Account>();
        lView = (CoordinatorLayout) findViewById(R.id.lLogin);
        TextView entry = new TextView(this);
        String password = usr.getPassword();
        String username = usr.getUsername();
        //accountEntry = "Username: " + username + " Password: " + password;
        //entry.setText(accountEntry);
        //lView.addView(entry);

        // populate with a list;
        //for(User someone : userT)

        WebInterface web = WebService.getService();

        Call<JsonArray> call = web.getAllAccounts(username, password);

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                int statusCode = response.code();
                //Account user = response.body();
                JsonArray body = response.body();
                Log.w("Apicall", "Status " + statusCode);
                Log.w("Apicall", "Result: " + body.toString());
                for(JsonElement i: body){
                    JsonObject acc = i.getAsJsonObject();
                    //acc.get("")
                }
                /*Log.w("Apicall", "body size " + body.size());
                for(JsonElement i : body){
                    Log.w("Apicall", i.toString());
                }*/
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

                break;
            case R.id.addAccounts:
                startActivity(new Intent(this, AddingAccounts.class));
                break;

        }
    }

    // This method will take in a Json response from
    // server and then populate the current table.
    public void updateAccountTable(JsonArray array){
        // hash map takes id and account

        // id format will row number * 10 + fieldnumber
        for(int i = 0; i < array.size(); i++){
            int id = i * 10;
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            row.setId(id);

            JsonObject acc = array.get(i).getAsJsonObject();
            Log.w("Apicall", "Object: " + acc.toString());

            // add account to accountTable.
            accountTable.put(id, new Account(acc));

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

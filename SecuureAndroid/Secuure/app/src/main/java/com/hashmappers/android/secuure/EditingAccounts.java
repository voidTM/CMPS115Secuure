package com.hashmappers.android.secuure;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import static com.hashmappers.android.secuure.R.id.titleList;

// Assuming an account has been passed over it will edit or delete the account
public class EditingAccounts extends AppCompatActivity implements View.OnClickListener {

    Button edit, delete, cancel;
    ImageButton imageButtonShowPass;
    EditText enterTitle, enterLogin, enterPassword, enterAdditionalNotes;
    TextView viewTitle, viewLogin;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeEditAccount;
    private Account acc;
    User usr;
    WebInterface web;
    boolean isClicked;
    //ArrayList<String> titles = new ArrayList<String>();
    //ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_accounts);
        usr = Global.getUser();

        // Initialize the variables used
        viewTitle = (TextView) findViewById(R.id.title);
        viewLogin = (TextView) findViewById(R.id.login);
        enterPassword = (EditText) findViewById(R.id.enterPassword);
        enterAdditionalNotes = (EditText) findViewById(R.id.enterAdditionalNotes);
        edit = (Button) findViewById(R.id.edit);
        delete = (Button) findViewById(R.id.delete);
        cancel = (Button) findViewById(R.id.buttonCancel);
        imageButtonShowPass = (ImageButton) findViewById(R.id.imageButtonShowPass);
        relativeEditAccount = (RelativeLayout) findViewById(R.id.relativeEditAccount);
        isClicked = false;
        acc = Global.getAcc();
        Log.w("check", acc.getAppName());
        Log.w("check", acc.getUsername());
        Log.w("check", acc.getPassword());
        Log.w("check", acc.getNote());
        web = WebService.getService();

        viewTitle.setText(acc.getAppName());
        viewLogin.setText(acc.getUsername());
        enterPassword.setText(acc.getPassword());
        enterAdditionalNotes.setText(acc.getNote());

        edit.setOnClickListener(this);
        delete.setOnClickListener(this);
        cancel.setOnClickListener(this);

        imageButtonShowPass.setOnClickListener(this);

        // Adapter
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,titles);
        //titleList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit:
                // Receive all the user attributes that are typed in for an password accounts
                String password = enterPassword.getText().toString();
                String notes = enterAdditionalNotes.getText().toString();
                //User accountData = new User(name, login, password);
                //After getting the updated data send to server
                Log.w("Changed", password);
                Log.w("Changed", notes);

                Call<String> call = web.editAccount(usr.getUsername(), usr.getPassword(),
                                            acc.getUsername(), acc.getAppName(), password, notes);

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        int statusCode = response.code();
                        Log.w("Apicall", "Status " + statusCode);
                        Log.w("Call Response,", response.body());
                        // if statusCode 200  start activity?
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        // Log error here since request failed
                        Log.e("Apicall", t.getMessage());
                    }
                });
                // Go to login page?
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.delete:
                // If you want to delete an entire account entry which was added
                AlertDialog.Builder builder = new AlertDialog.Builder(EditingAccounts.this);

                builder.setMessage("Are you sure you want to delete entry?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteAcc();
                            }
                        })
                        .setNegativeButton("NO", null);
                AlertDialog alert = builder.create();
                alert.show();
                // Go to login page?
                break;
            case R.id.buttonCancel:
                goBack();
                break;
            case R.id.imageButtonShowPass:
                Log.d("Log", "clicked?" + isClicked);
                if(isClicked) {
                    isClicked = false;
                    enterPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    isClicked = true;
                    enterPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

        }
    }

    // Deletes the account being edited from the database.
    public void deleteAcc(){
        Call<String> call = web.deleteAccount(usr.getUsername(), usr.getPassword(),
                acc.getUsername(), acc.getAppName());

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int statusCode = response.code();
                Log.w("Apicall", "Status " + statusCode);
                // Debug message from server.
                Log.w("Response", response.body());
                // if statusCode 200  start activity?
                goBack();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Log error here since request failed
                Log.e("Apicall", t.getMessage());
            }
        });
    }

    void goBack(){startActivity(new Intent(this, Login.class));}

}
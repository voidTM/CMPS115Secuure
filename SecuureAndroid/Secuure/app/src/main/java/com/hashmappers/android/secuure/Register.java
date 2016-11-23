package com.hashmappers.android.secuure;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bSignUp;
    EditText etFirstName, etLastName, etUsername, etPassword, etConfirmPassword;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize the variables for registering a new account
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        bSignUp = (Button) findViewById(R.id.bSignUp);
        linearLayout = (LinearLayout) findViewById(R.id.linear);

        bSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSignUp:
                // Receive all the user attributes that are typed in and converts them into string
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                //User registeredData = new User(name, username, password);
                User userID = Global.getUser();
                WebInterface web = WebService.getService();
                userID.setUser(firstName, username, password);
                // Add to user table
                userID.printUser();
                // registers user with database

                // Checks if the password that is entered is the same or not same when you confirm it, displays a popup screen
                // Shouldn't this happen before adding the user?
                if (!password.equals(confirmPassword) || password.length() == 0 || confirmPassword.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);

                    builder.setMessage("Error: Passwords Don't Match")
                            .setPositiveButton("OK", null);
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    // Registers user with server
                    web.registerUser(username, password, firstName, lastName).enqueue(new Callback<String>() {
                        @Override
                        // check for any messages
                        public void onResponse(Call<String> call, Response<String> response) {
                            //api success
                            //Boolean sucess //= response.body();
                            Log.w("Apicall", "Successful register");
                            Log.w("ApiRegister", "" +response.body());
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("Apicall", "" + t.getMessage());
                        }
                    });
                    // Successful log in message display
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);

                    builder.setMessage("Successful")
                            .setPositiveButton("BACK TO LOG IN", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(Register.this, MainActivity.class));
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
                break;
        }
    }

}

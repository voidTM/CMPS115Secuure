package com.hashmappers.android.secuure;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bSignUp;
    EditText etFirstName, etLastName, etUsername, etPassword, etConfirmPassword;
    private PopupWindow popupWindowFail;
    private PopupWindow popupWindowSuc;
    private LayoutInflater layoutInflater;
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
                UserTable userT = Global.getUserT();
                WebInterface web = WebService.getService();
                userID.setUser(firstName, username, password);
                // Add to user table
                userT.addUser(userID);

                // registers user with database
                web.registerUser(username, firstName, password).enqueue(new Callback<Boolean>() {
                @Override
                    // check for any messages
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        //api success
                        //Boolean sucess //= response.body();

                        Log.w("Apicall", "Successful register call");
                    }

                @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Log.e("Apicall", t.getMessage());
                    }
                });


                // Checks if the password that is entered is the same or not same when you confirm it, displays a popup screen
                // Shouldn't this happen before adding the user?
                if (!password.equals(confirmPassword) || password.length() == 0 || confirmPassword.length() == 0) {
                    // Get the instance of the LayoutInflator
                    layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    ViewGroup container1 = (ViewGroup) layoutInflater.inflate(R.layout.popupinvalidsignup, null);

                    // Display the popup window in the center of screen if you fail to log in correctly
                    popupWindowFail = new PopupWindow(container1, 500, 220, true);
                    popupWindowFail.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);

                    // To exit the popup window, hit the 'OK' button
                    Button buttonOk = (Button) container1.findViewById(R.id.buttonOk);
                    buttonOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindowFail.dismiss();
                        }
                    });
                } else {
                    // Get the instance of the LayoutInflator
                    layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    ViewGroup container2 = (ViewGroup) layoutInflater.inflate(R.layout.popupvalidsignup, null);

                    // Display the popup window in the center of screen if you fail to log in correctly
                    popupWindowSuc = new PopupWindow(container2, 500, 250, true);
                    popupWindowSuc.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);

                    // To exit the popup window and go back to the log in screen, hit the 'BACK TO LOG IN' button
                    Button buttonBackLogin = (Button) container2.findViewById(R.id.buttonBackLogin);
                    buttonBackLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Register.this, MainActivity.class));
                        }
                    });
                }
                break;
        }
    }
}

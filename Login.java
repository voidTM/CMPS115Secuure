
package com.hashmappers.android.secuure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button bLogout;
    //UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);

        //userLocalStore = new UserLocalStore(this);
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
/*                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);*/
                startActivity(new Intent(this, Logout.class));
                break;
        }
    }
}
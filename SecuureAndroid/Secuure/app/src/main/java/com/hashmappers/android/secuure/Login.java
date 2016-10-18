
package com.hashmappers.android.secuure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button bLogout;
    String name;
    String accountEntry;
    TextView userWelcome;
    //UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);
        userWelcome = (TextView) findViewById(R.id.textUserID);
        User usr = Global.getUser();
        UserTable userT = Global.getUserT();
        name = usr.getName();

        userWelcome.setText("Welcome " + name);
        //userLocalStore = new UserLocalStore(this);

        LinearLayout lView = (LinearLayout)findViewById(R.id.lLogin);
        TextView entry = new TextView(this);
        String password = usr.getPassword();
        String username = usr.getUsername();
        accountEntry = "Username: " + username + " Password: " + password;
        entry.setText(accountEntry);
        lView.addView(entry);

        // populate with a list;
        //for(User someone : userT)
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
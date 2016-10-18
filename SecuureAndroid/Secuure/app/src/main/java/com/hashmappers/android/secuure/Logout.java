package com.hashmappers.android.secuure;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.microedition.khronos.opengles.GL;

public class Logout extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    FileOutputStream fos;
    ObjectOutputStream oos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User storeUser;
        AccountTable storeTable;
        setContentView(R.layout.activity_logout);
        bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(this);

        storeTable = Global.getAccountT();
        storeUser = Global.getUser();

        // User data stored in shared preference


        // Write data to file
        // For hashtable
        try {
            fos = openFileOutput("usersfile", Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(storeTable);

            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then clear table.
        Global.reset();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}

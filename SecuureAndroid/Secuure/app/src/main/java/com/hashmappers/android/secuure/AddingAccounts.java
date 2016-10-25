package com.hashmappers.android.secuure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.Toast;

import java.util.ArrayList;

import static com.hashmappers.android.secuure.R.id.titleList;

public class AddingAccounts extends AppCompatActivity implements View.OnClickListener {

    Button addList;
    ImageButton imageButtonGP;
    EditText enterTitle, enterLogin, enterPassword, enterAdditionalNotes;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeAddAccount;
    //ArrayList<String> titles = new ArrayList<String>();
    //ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_accounts);

        // Initialize the variables used
        enterTitle = (EditText) findViewById(R.id.enterTitle);
        enterLogin = (EditText) findViewById(R.id.enterLogin);
        enterPassword = (EditText) findViewById(R.id.enterPassword);
        enterAdditionalNotes = (EditText) findViewById(R.id.enterAdditionalNotes);
        addList = (Button) findViewById(R.id.addList);
        imageButtonGP = (ImageButton) findViewById(R.id.imageButtonGP);
        relativeAddAccount = (RelativeLayout) findViewById(R.id.relativeAddAccount);

        addList.setOnClickListener(this);
        imageButtonGP.setOnClickListener(this);

        // Adapter
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,titles);
        //titleList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addList:
                // Receive all the user attributes that are typed in for an password accounts
                String title = enterTitle.getText().toString();
                String login = enterLogin.getText().toString();
                String password = enterPassword.getText().toString();
                String notes = enterAdditionalNotes.getText().toString();
                //User accountData = new User(name, login, password);

                startActivity(new Intent(this, Login.class));
                break;
            case R.id.imageButtonGP:
                // Get the instance of the LayoutInflator
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popupgenpassword, null);

                // Display the popup window in the center of screen if you fail to log in correctly
                popupWindow = new PopupWindow(container, 800, 800, true);
                popupWindow.showAtLocation(relativeAddAccount, Gravity.CENTER, 0, 0);

                // Hit the 'OK' button to save the generated password
                Button buttonOk = (Button) container.findViewById(R.id.buttonOk);
                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                // Hit the "CANCEL' button to not save the generated password
                Button buttonCancel = (Button) container.findViewById(R.id.buttonCancel);
                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                break;
        }
    }


/*    private void addNewAccountListFunction() {
        String name = nameTxt.getText().toString();

        if (!name.isEmpty() && name.length()>0) {
            //ADD
            adapter.add(name);

            //refresh
            adapter.notifyDataSetChanged();

            //clear
            nameTxt.setText(" ");

            Toast.makeText(getApplicationContext(),"Added " + name, Toast.LENGTH_SHORT.show());
        }else {
            Toast.makeText(getApplicationContext(),"Nothing to Add", Toast.LENGTH_SHORT.show());
        }
    }

    //Update
    private void update() {
    }

    String name = nameTxt.getText().toString();
    // choose the position of selected item
    int pos = list.getCheckedItemPosition();
    if(!name.isEmpty()&&name.length()>0)

    {
        //remove item
        adapter.remove(name.get(pos));

//insert
        adapter.insert(name, pos);

// refresh
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Updated" + name, Toast.LENGTH_SHORT).show();
    }*/
}
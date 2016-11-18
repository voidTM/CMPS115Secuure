package com.hashmappers.android.secuure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    Button edit, delete;
    EditText enterTitle, enterLogin, enterPassword, enterAdditionalNotes;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeEditAccount;
    //ArrayList<String> titles = new ArrayList<String>();
    //ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_accounts);

        // Initialize the variables used
        enterTitle = (EditText) findViewById(R.id.enterTitle); // Should not be editable
        enterLogin = (EditText) findViewById(R.id.enterLogin); // Should not be editable
        enterPassword = (EditText) findViewById(R.id.enterPassword);
        enterAdditionalNotes = (EditText) findViewById(R.id.enterAdditionalNotes);
        edit = (Button) findViewById(R.id.edit);
        delete = (Button) findViewById(R.id.delete);
        relativeEditAccount = (RelativeLayout) findViewById(R.id.relativeEditAccount);

        edit.setOnClickListener(this);
        delete.setOnClickListener(this);

        // Adapter
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,titles);
        //titleList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit:
                // Receive all the user attributes that are typed in for an password accounts
                String title = enterTitle.getText().toString();
                String login = enterLogin.getText().toString();
                String password = enterPassword.getText().toString();
                String notes = enterAdditionalNotes.getText().toString();
                //User accountData = new User(name, login, password);
                User usr = Global.getUser();
                //After getting the updated data send to server
                WebInterface web = WebService.getService();
                Call<ResponseBody> call = web.editAccount(usr.getUsername(), usr.getPassword(),
                                            title, login, password, notes);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        int statusCode = response.code();
                        Log.w("Apicall", "Status " + statusCode);
                        // if statusCode 200  start activity?
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        // Log error here since request failed
                        Log.e("Apicall", t.getMessage());
                    }
                });
                // Go to login page?
                startActivity(new Intent(this, AddingAccounts.class));
                break;
            case R.id.delete:
                // Get the instance of the LayoutInflator
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popupdelete, null);

                // Display the popup window in the center of screen if you fail to log in correctly
                popupWindow = new PopupWindow(container, 600, 300, true);
                popupWindow.showAtLocation(relativeEditAccount, Gravity.CENTER, 0, 0);

                // Hit the 'YES' button to delete account info go back to user page
                Button bYes = (Button) container.findViewById(R.id.bYes);
                bYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                // Hit the 'NO' button to cancel your delete choice
                Button bNo = (Button) container.findViewById(R.id.bNo);
                bNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                // Go to login page?
                break;
        }
    }
}
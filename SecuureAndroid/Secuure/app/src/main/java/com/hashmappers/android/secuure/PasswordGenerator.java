package com.hashmappers.android.secuure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.Random;

public class PasswordGenerator extends AppCompatActivity implements OnClickListener {
    CheckBox  radioCap, radioNum, radioSym;
    TextView textViewPass, textLength;
    Button buttonCancel, buttonOk;
    ImageButton imageButtonRefresh;
    SeekBar seekBarLength;
    Account acc;
    int progress = 8; // Set the minimum length of password to be 8

    String genPass = "";
    private int cap, sym, numbs;
    // Flags determine to help see if a box is checked which gets used to generate a password
    private boolean flagCap = false, flagNum = false, flagSym = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_generator);

        cap = 0; sym = 0; numbs = 0;
        acc = Global.getAcc();
        // Initialize all the variables on the page
        radioCap = (CheckBox) findViewById(R.id.radioCap);
        radioNum = (CheckBox) findViewById(R.id.radioNum);
        radioSym = (CheckBox) findViewById(R.id.radioSym);
        textViewPass = (TextView) findViewById(R.id.textView);
        imageButtonRefresh = (ImageButton) findViewById(R.id.imageButtonRefresh);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        buttonOk = (Button) findViewById(R.id.buttonOk);

        radioCap.setOnClickListener(this);
        radioNum.setOnClickListener(this);
        radioSym.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        buttonOk.setOnClickListener(this);
        imageButtonRefresh.setOnClickListener(this);

        seekBarLength = (SeekBar) findViewById(R.id.seekBarLength);
        // Sets the max password length to being 20 and minimum to 8
        seekBarLength.setMax(12);
        seekBarLength.setProgress(progress-8);

        textLength = (TextView) findViewById(R.id.textLength);
        textLength.setText("" + progress);

        seekBarLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                progress = i+8;
                textLength.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Should have some capitol letters in the generated password
            case R.id.radioCap:
                cap +=1;
                cap = cap % 2;
                if (cap == 1) {
                    flagCap = true;
                } else {
                    flagCap = false;
                }
                Log.d("Check cap", " cap" +cap);
                break;

            // Should have some numbers in the generated password
            case R.id.radioNum:
                numbs +=1;
                numbs = numbs % 2;
                if (numbs == 1) {
                    flagNum = true;
                } else {
                    flagNum = false;
                }
                Log.d("Check numbs", " numbs" +numbs);
                break;

            // Should have some symbols in the generated password
            case R.id.radioSym:
                sym +=1;
                sym = sym % 2;
                if (sym == 1) {
                    flagSym = true;
                } else {
                    flagSym = false;
                }
                Log.d("Check sym", " Sym" +sym);
                break;

            case R.id.buttonCancel:
                startActivity(new Intent(this,AddingAccounts.class));
                break;
            case R.id.buttonOk:
                //Intent i = new Intent(this, AddingAccounts.class);
                //i.putExtra("gPass",genPass);
                //startActivity(i);
                startActivity(new Intent(this,AddingAccounts.class));
                break;

            // Should call to create a new password with the specific specifications
            case R.id.imageButtonRefresh:
                generatePass();
                break;
        }
    }

    // Generates a random password based on user input
    private void generatePass () {

        String genPass = randomString(seekBarLength.getProgress()+8, flagCap, flagNum, flagSym);
        acc.changePassword(genPass);
        textViewPass.setText(genPass);

    }

    /* This function creates a random string depending on the what the user wants to include in their
           * random generated password. */
    public String randomString (int progress, boolean noOfCapitals, boolean noOfNumbers, boolean noOfSymbols) {
        String characters = "";

        // Various cases of choosing capital letters, numbers, or symbols to be included
        if (flagCap && !flagNum && !flagSym) {
            characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        } else if (!flagCap && flagNum && !flagSym) {
            characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        } else if (!flagCap && !flagNum && flagSym) {
            characters = "abcdefghijklmnopqrstuvwxyz!@#$%&^*()";
        } else if (flagCap && flagNum && !flagSym) {
            characters = "abcdefghijklmnopqrstuvwxyABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        } else if (flagCap && !flagNum && flagSym) {
            characters = "abcdefghijklmnopqrstuvwxyABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()";
        } else if (!flagCap && flagNum && flagSym) {
            characters = "abcdefghijklmnopqrstuvwxy0123456789!@#$%^&*()";
        } else if (flagCap && flagNum && flagSym) {
            characters = "abcdefghijklmnopqrstuvwxyABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()";
        } else if (!flagCap && !flagNum && !flagSym) {
            characters = "abcdefghijklmnopqrstuvwxyz";
        }

        // This creates a random string using one of the cases above
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < progress) {
            int randNum = (int) (rnd.nextFloat()* characters.length());
            salt.append(characters.charAt(randNum));
        }

        genPass = salt.toString();
        return genPass;
    }
}

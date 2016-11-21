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
    int progress = 8;

   /* int pound = 35;
    int excl = 33;
    int at = 64;
    int dollar = 36;
    int perc = 37;
    int carrot = 94;
    int amper = 38;
    int ast = 42;

    // Declaring the arrays of ascii values for corresponding set of characters
    private char[] symbols;
    private char[] numbers;
    private char[] capitols;
    private char[] lowerCase;
    //private char symbolArr = [pound, excl, at, dollar, perc, carrot, amper, ast];
    */

    private String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String lowerCase = "abcdefghijklmnopqrstuvwxyz";
    private String num = "0123456789";
    private String symbol = "!@#$%^&*";
    private String mainString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";

    private int cap = 0;
    private int sym = 0;
    private int numbs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_generator);
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
        textLength.setText(""+progress);

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
                //textViewPass.setText("Capitol");

                cap +=1;
                cap = cap % 2;
                Log.d("Check cap", " cap" +cap);
                break;

            // Should have some numbers in the generated password
            case R.id.radioNum:
                //textViewPass.setText("Number");
                numbs +=1;
                numbs = numbs % 2;
                Log.d("Check numbs", " numbs" +numbs);
                break;

            // Should have some symbols in the generated password
            case R.id.radioSym:
                //textViewPass.setText("Symbol");
                sym +=1;
                sym = sym % 2;
                Log.d("Check sym", " Sym" +sym);
                break;

            case R.id.buttonCancel:
                startActivity(new Intent(this,AddingAccounts.class));
                break;
            case R.id.buttonOk:
                startActivity(new Intent(this,AddingAccounts.class));
                break;

            // Should call to create a new password with the specific specifications
            case R.id.imageButtonRefresh:
                if ((cap == 1) || (numbs == 1)|| (sym == 1)) {
                    generatePass(seekBarLength.getProgress()+8, cap, numbs, sym);
                } else {
                    generatePass(seekBarLength.getProgress()+8, 0, 0, 0);
                }
                break;
        }
    }

    // Generates a random password based on user input
    public void generatePass(int progress, int noOfCapitals, int noOfNumbers, int noOfSymbols) {
        Random rnd = new Random();
        int randNum = rnd.nextInt(progress);
        // int randNum = rnd.nextInt(Integer.MAX_VALUE) % mainString.length();
        char[] pswd = new char[progress];
        int index = 0;
        if (noOfCapitals == 1) {
            index = getNextIndex(rnd, randNum, pswd);
            pswd[index] = caps.charAt(rnd.nextInt(caps.length()));
        } else if (noOfNumbers == 1) {
            index = getNextIndex(rnd, randNum, pswd);
            pswd[index] = num.charAt(rnd.nextInt(num.length()));
        } else if (noOfSymbols == 1) {
            index = getNextIndex(rnd, randNum, pswd);
            pswd[index] = symbol.charAt(rnd.nextInt(symbol.length()));
        }

        for (int i = 0; i < progress; i++) {
            if (pswd[i] == 0) {
                pswd[i] = lowerCase.charAt(rnd.nextInt(lowerCase.length()));
            }
        }
        String genPass = String.valueOf(pswd);
        textViewPass.setText(genPass);
    }

    private int getNextIndex(Random rnd, int randNum, char[] pswd) {
        int index = rnd.nextInt(randNum);
        while (pswd[index = rnd.nextInt(randNum)] != 0);
        return index;
    }
}

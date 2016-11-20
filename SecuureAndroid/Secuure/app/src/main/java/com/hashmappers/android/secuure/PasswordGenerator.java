package com.hashmappers.android.secuure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.Random;

public class PasswordGenerator extends AppCompatActivity implements OnClickListener {
    RadioButton  radioCap, radioNum, radioSym;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_generator);

        // Initialize all the variables on the page
        radioCap = (RadioButton) findViewById(R.id.radioCap);
        radioNum = (RadioButton) findViewById(R.id.radioNum);
        radioSym = (RadioButton) findViewById(R.id.radioSym);
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
                break;

            // Should have some numbers in the generated password
            case R.id.radioNum:
                //textViewPass.setText("Number");
                break;

            // Should have some symbols in the generated password
            case R.id.radioSym:
                //textViewPass.setText("Symbol");
                break;

            case R.id.buttonCancel:
                startActivity(new Intent(this,AddingAccounts.class));
                break;
            case R.id.buttonOk:
                startActivity(new Intent(this,AddingAccounts.class));
                break;

            // Should call to create a new password with the specific specifications
            case R.id.imageButtonRefresh:
                textViewPass.setText("New password");
                break;
        }
    }


    /*
    // Generates array of ascii values
    public void generateAsciiArrays() {

    }

    // Converts array(s) of ascii values to their corresponding character values
    public void generateArrayAll() {

    }
    */


    // Generates a random password based on user input
/*    public void generatePass(int len) {
        String[] array = new String[10];
        Random rnd = new Random();
        int randNum = rnd.nextInt() % array.length;
        char[] pswd = new char[randNum];
        String genPass = String.valueOf("s");
        textViewPass.setText(genPass);
        while(true) {
            if () {

            } else {

            }
        }

    }*/

/*    public char[] generatePass(int len) {
        char[] pswd = new char[len];


        String genPass = String.valueOf("s");
        textViewPass.setText(genPass);

        return pswd;
    }*/
}

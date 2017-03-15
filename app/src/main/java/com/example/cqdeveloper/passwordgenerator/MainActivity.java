package com.example.cqdeveloper.passwordgenerator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
//?? Test cases

public class MainActivity extends AppCompatActivity {

    private Button passwordGenerator;
    private Button copyPassword;
    private EditText password_length;
    private EditText number_of_chars;
    private EditText number_of_numbers;
    private TextView password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordGenerator = (Button) findViewById(R.id.generate);
        copyPassword = (Button) findViewById(R.id.copy);

        password_length = (EditText) findViewById(R.id.password_length);
        number_of_chars = (EditText) findViewById(R.id.number_of_chars);
        number_of_numbers = (EditText) findViewById(R.id.number_of_numbers);

        password = (TextView) findViewById(R.id.password);

        //Generates the password
        passwordGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rawLength = password_length.getText().toString();
                String rawNum_chars = number_of_chars.getText().toString();
                String rawNum_numbers = number_of_numbers.getText().toString();

                int length = 0;
                int num_chars;
                int num_numbers;
                boolean password_has_length = false;
                boolean reasonable_lengths = false;


                if (rawLength.matches("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a password length", Toast.LENGTH_SHORT).show();
                }
                else {
                    length = Integer.valueOf(password_length.getText().toString());
                    password_has_length = true;
                }

                if (password_has_length && length <= 30) {
                    if (rawNum_chars.matches("")) {
                        num_chars = 0;
                    }
                    else {
                        num_chars = Integer.valueOf(number_of_chars.getText().toString());
                    }

                    if (rawNum_numbers.matches("")) {
                        num_numbers = 0;
                    }
                    else {
                        num_numbers = Integer.valueOf(number_of_numbers.getText().toString());
                    }

                    //Ensures that the number of symbols and characters do not exceed the total
                    //password length | Are the values in our bounds?
                    if (length < num_chars + num_numbers) {
                        Toast.makeText(getApplicationContext(), "Maybe try a larger password length?", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        reasonable_lengths = true;
                    }

                    if (reasonable_lengths) {
                        int num_letters = length - (num_chars + num_numbers);
                        String generated_password = "";

                        generated_password = PasswordHelper.generatePassword(length, num_chars, num_numbers);

                        password.setText(generated_password);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Password cannot exceed 30 characters", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Copies the generated password onto clipboard so that user could paste elsewhere
        copyPassword.setOnClickListener(new View.OnClickListener() {
            //SOURCE
            //https://www.learn2crack.com/2014/01/android-using-clipboard-copy-paste-data.html
            @Override
            public void onClick(View arg0) {
                String CopyText;
                CopyText = password.getText().toString();

                //Does our password have any length? If not, then we have nothing to copy
                if (CopyText.length() != 0){
                        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        android.content.ClipData clip = android.content.ClipData.newPlainText("Clip",CopyText);

                        Toast.makeText(getApplicationContext(), "Password copied to clipboard", Toast.LENGTH_SHORT).show();
                        clipboard.setPrimaryClip(clip);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Nothing to copy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
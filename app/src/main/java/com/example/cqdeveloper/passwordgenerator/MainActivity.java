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
//?? Test cases, ensure max length

public class MainActivity extends AppCompatActivity {

    private Button passwordGenerator;
    private Button copyPassword;
    private EditText password_length;
    private EditText number_of_chars;
    private EditText number_of_numbers;
    private TextView password;

    private char[] symbols = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '?', '/',
            '+', '-', ';', ':', '_', '=', '`', '~'};

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

                        //HELPER FUNCTIONS
                        generated_password = add_letters(generated_password, num_letters);
                        generated_password = add_chars(generated_password, num_chars);
                        generated_password = add_numbers(generated_password, num_numbers);
                        generated_password = randomize(generated_password);

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

    /**
     * Adds letters to generated password, with random capitalization
      * @param original_password The password tailored to the user's inputs
     * @param num_letters Number of letters to be inputted into the password
     * @return A string of random letters with random capitalization
     */
    private String add_letters(String original_password, int num_letters) {
        int firstIndex = 0;
        int alphabet = 26;
        //Add randomly selected lowercase letters to the password according to the
        //amount specified by the user
        for (int letterIndex = firstIndex; letterIndex < num_letters; letterIndex++) {
            Random multiplier = new Random();
            char letter = (char) (multiplier.nextInt(alphabet) + 'a');

            //Capitalize random letters to improve security
            //1000, 3, % == Purely random values in an unusual circumstance to increase safety of password
            if ((multiplier.nextInt(1000)) % 3 == 0) {
                letter = Character.toUpperCase(letter);
            }
            original_password += letter;
        }
        return original_password;
    }

    /**
     * Adds characters(symbols) to generated password
     * @param original_password The password tailored to the user's inputs
     * @param num_chars Number of characters to be inputted into the password
     * @return A string of random letters with random capitalization followed by a random series of symbols (per length)
     */
    private String add_chars(String original_password, int num_chars) {
        int firstIndex = 0;
        //Add randomly selected symbols from our array according to the amount specified by the user
        for (int charIndex = firstIndex; charIndex < num_chars; charIndex++) {
            Random multiplier = new Random();
            char symbol = symbols[(multiplier.nextInt(symbols.length))];

            original_password += symbol;
        }
        return original_password;
    }

    /**
     * Adds characters(symbols) to generated password
     * @param original_password The password tailored to the user's inputs
     * @param num_numbers Number of numerical values to be inputted into the password
     * @return A string of random letters with random capitalization followed by a random series of symbols (per length) and
     * a random series of numbers (per length)
     */
    private String add_numbers(String original_password, int num_numbers) {
        //Add randomly selected numbers according the amount specified by the user
        for (int numIndex = 0; numIndex < num_numbers; numIndex++) {
            Random multiplier = new Random();

            original_password += multiplier.nextInt(10);
        }
        return original_password;
    }

    /**
     * Takes the password, currently in form of
     * Letters | Symbols | Numbers
     * and rearranges them to intermingle the characters and further improve the safety of the password
     * @param original_password Take in the password with the assigned random letters, symbols, and numbers
     * @return new_password which contains the same characters but now in different positions
     */
    private String randomize(String original_password) {
        String new_password = "";
        int firstIndex = 0;
        int passwordLength = original_password.length();

        //Initialize new_password to the length of the password
        //Now, we can begin moving elements from original_password around
        for (int addIndex = firstIndex; addIndex < passwordLength; addIndex++) {
            char genericPlaceFiller = 'x';
            new_password += genericPlaceFiller;
        }

        Random charSelector = new Random();
        //Iterates through new_password and changes its elements bsaed on what is randomly
        //selected from original_password
        for (int changeChar = firstIndex; changeChar < passwordLength; changeChar++) {
            //"Mutable" because we will be using substrings of original_password in this loop
            int mutablePasswordLength = original_password.length();
            char selectedChar = original_password.charAt(charSelector.nextInt(mutablePasswordLength));
            int firstChar = 1;

            //Add the selected character from original_password and remove one of the
            //placeholder characters originally in new_password
            new_password += selectedChar;
            new_password = new_password.substring(firstChar);

            int indexOfChar = original_password.indexOf(selectedChar);
            int charAfterThisIndex = indexOfChar + 1;

            //Substring ensures that the same character is not used again
            original_password = original_password.substring(firstIndex, indexOfChar) +
                    original_password.substring(charAfterThisIndex);
        }
        return new_password;
    }
}
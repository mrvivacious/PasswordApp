package com.example.cqdeveloper.passwordgenerator;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by vbhook2 on 3/14/17.
 */
public class PasswordHelperTest {

    /**
     * Tests to see if a randomly generated password of length 10 with hard-coded properties contains the appropriate characters
     * @throws Exception
     */
    @Test
    public void generatePassword() throws Exception {
        int length = 10;
        int symbols = 3;
        int numbers = 3;
        int letters = 4;

        String password = PasswordHelper.generatePassword(length, symbols, numbers);

        int charCount = 0;
        int numCount = 0;
        int letterCount = 0;
        int firstIndex = 0;
        for (int index = firstIndex; index < password.length(); index++) {
            String character = password.substring(index, index + 1);

            if (letterCheck(character)) {
                letterCount++;
            }
            if (symbolCheck(character)) {
                charCount++;
            }
            if (numberCheck(character)) {
                numCount++;
            }
        }

        assertEquals(letters, letterCount);
        assertEquals(symbols, charCount);
        assertEquals(numbers, numCount);
    }

    /**
     * Tests to see if a completely random password with a length of at least 13 contains the values randomly assigned to it
     * @throws Exception
     */
    @Test
    public void generateRandomPassword() throws Exception {
        Random val = new Random();

        //At least 13 because symbols + numbers has an upper bound of 10, exclusive, and we want to
        //ensure that we don't get a negative value for length
        int length = val.nextInt(20) + 10;
        int symbols = val.nextInt(6);
        int numbers = val.nextInt(6);
        int letters = length - (symbols + numbers);

        String password = PasswordHelper.generatePassword(length, symbols, numbers);

        int charCount = 0;
        int numCount = 0;
        int letterCount = 0;
        int firstIndex = 0;
        for (int index = firstIndex; index < password.length(); index++) {
            //Using substring because .charAt returns a char type and .contains is only usable
            //with String types
            String character = password.substring(index, index + 1);

            if (letterCheck(character)) {
                letterCount++;
            }
            if (symbolCheck(character)) {
                charCount++;
            }
            if (numberCheck(character)) {
                numCount++;
            }
        }

        assertEquals(letters, letterCount);
        assertEquals(symbols, charCount);
        assertEquals(numbers, numCount);
    }

    /**
     * If the char at some index is an alphabet letter, increment letterCount
     * @param Char the character at some index
     * @return whether or not this character is a letter
     */
    private boolean letterCheck (String Char) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (alphabet.contains(Char)) {
            return true;
        }
        return false;
    }

    /**
     * If the char at some index is a symbol (from our set), increment charCount
     * @param Char the character at some index
     * @return whether or not this character is a symbol within our set
     */
    private boolean symbolCheck(String Char) {
        String symbols = "!@#$%^&*()?/+-;:_=`~";
        if (symbols.contains(Char)) {
            return true;
        }
        return false;
    }

    /**
     * If the char at some index is a number, increment numCount
     * @param Char the character at some index
     * @return whether or not this character is a number
     */
    private boolean numberCheck(String Char) {
        String numbers = "0123456789";
        if (numbers.contains(Char)) {
            return true;
        }
        return false;
    }
}
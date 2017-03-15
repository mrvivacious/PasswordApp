package com.example.cqdeveloper.passwordgenerator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cqdeveloper on 3/14/17.
 */
public class PasswordHelperTest {
    @Test
    public void generatePassword() throws Exception {
        int length = 10;
        int characters = 3;
        int numbers = 3;
        int letters = 4;

        String password = PasswordHelper.generatePassword(length, characters, numbers);

        int firstIndex = 0;
        for (int index = firstIndex; index < password.length(); index++) {

        }
    }

}
package com.example.cqdeveloper.passwordgenerator;

/**
 * Created by cqdeveloper on 5/25/17.
 */

public class Account {
    private String name;
    private String password;

    //Constructor
    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //GETTERS
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}

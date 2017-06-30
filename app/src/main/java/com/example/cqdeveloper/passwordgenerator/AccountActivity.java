package com.example.cqdeveloper.passwordgenerator;

/**
 * Created by vbhook2 on 3/16/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class AccountActivity extends AppCompatActivity {

    private ListView accountList;
    private FloatingActionButton addAccount;
    private Button passwordGenerator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_list);

        accountList = (ListView) findViewById(R.id.lv_accounts);
        addAccount = (FloatingActionButton) findViewById(R.id.fab_add);
        passwordGenerator = (Button) findViewById(R.id.b_password_generator);

        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(v.getContext(), AddActivity.class);
                v.getContext().startActivity(add);
            }
        });

        passwordGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent password = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(password);
            }
        });

        // Get a reference to the ListView, and attach the adapter to the listView.
        accountList.setAdapter(AddActivity.accountAdapter);

    }
}

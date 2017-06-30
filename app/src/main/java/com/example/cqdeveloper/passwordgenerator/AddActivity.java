package com.example.cqdeveloper.passwordgenerator;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    private FloatingActionButton finishActivity;
    private EditText title;
    private EditText statistic;
    public static final ArrayList<Account> Accounts = new ArrayList<Account>();
    public static AccountAdapter accountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setTitle("Add an account");

        title = (EditText) findViewById(R.id.et_name);
        statistic = (EditText) findViewById(R.id.et_password);
        finishActivity = (FloatingActionButton) findViewById(R.id.fab_finish);

        accountAdapter = new AccountAdapter(this, Accounts);

        finishActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finish = new Intent(v.getContext(), AccountActivity.class);

                String accountTitle = title.getText().toString();
                String accountPassword = statistic.getText().toString();

                Accounts.add(new Account(accountTitle, accountPassword));

                v.getContext().startActivity(finish);
            }
        });
    }
}

package com.example.cqdeveloper.passwordgenerator;

/**
 * Created by vbhook2 on 3/16/17.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AccountActivity extends AppCompatActivity {

    private Button buttonGenerate;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        buttonGenerate = (Button) findViewById(R.id.copy);

        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.account_list);

        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
                Intent passwordScreen = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(passwordScreen);
            }
        });

    }
}

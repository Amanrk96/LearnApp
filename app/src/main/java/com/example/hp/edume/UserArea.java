package com.example.hp.edume;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserArea extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        TextView welcome = (TextView) findViewById(R.id.welcome);

        Button learn = (Button) findViewById(R.id.btn_learn);
        learn.setOnClickListener(this);
        Button askqsn = (Button) findViewById(R.id.btn_qsn);
        askqsn.setOnClickListener(this);
        Button postans = (Button) findViewById(R.id.btn_ans);
        postans.setOnClickListener(this);

        Button logout = (Button) findViewById(R.id.btn_logout);
        logout.setOnClickListener(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String msg = "Welcome " + name;
        welcome.setText(msg);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_learn: {
                Intent ilearn = new Intent(UserArea.this, QnsAnsActivity.class);
                startActivity(ilearn);
                break;
            }
            case R.id.btn_qsn: {
                Intent intent = getIntent();
                String name = intent.getStringExtra("name");
                Intent iqsn = new Intent(UserArea.this, SubmitQsn.class);
                iqsn.putExtra("name", name);
                startActivity(iqsn);
                break;
            }

            case R.id.btn_ans: {
                Intent intent = getIntent();
                String name = intent.getStringExtra("name");
                Intent ipost = new Intent(UserArea.this, PostAns.class);
                ipost.putExtra("name", name);
                startActivity(ipost);
                break;
            }
            case R.id.btn_logout: {
                Intent ilogout = new Intent(UserArea.this, LoginActivity.class);
                startActivity(ilogout);
                break;
            }
        }
    }
}
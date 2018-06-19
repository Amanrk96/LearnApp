package com.example.hp.edume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements OnClickListener {
    Button teacher, student, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        teacher = (Button) findViewById(R.id.btn_teacher);
        teacher.setOnClickListener(this);

        student = (Button) findViewById(R.id.btn_student);
        student.setOnClickListener(this);

        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login:
                Intent ilogin = new Intent(LoginActivity.this, LoginMain.class);
                startActivity(ilogin);
                break;
            case R.id.btn_teacher:
                Intent iteacher = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(iteacher);
                break;
            case R.id.btn_student:
                Intent istudent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(istudent);
                break;
        }
    }
}
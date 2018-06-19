package com.example.hp.edume;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    EditText rmail, rpassword, rname;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rname = (EditText) findViewById(R.id.rname);
        rmail = (EditText) findViewById(R.id.rmail);
        rpassword = (EditText) findViewById(R.id.rpassword);

        signup = (Button) findViewById(R.id.btn_signup);
        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = rname.getText().toString();
                String password = rpassword.getText().toString();
                String email = rmail.getText().toString();

                if (ifEmpty(rname, name) | ifEmpty(rmail, email) | ifEmpty(rpassword, password)) {
                    return;
                } else {


                    Response.Listener<String> responselistener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            try {
                                JSONObject jsonResponse = new JSONObject(s);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    Intent i = new Intent(RegisterActivity.this, LoginMain.class);
                                    RegisterActivity.this.startActivity(i);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("Registration Failed!");
                                    builder.setNegativeButton("Retry", null);
                                    builder.create();
                                    builder.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    RegisterRequest registerRequest = new RegisterRequest(name, password, email, responselistener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                }
            }
        });
    }

    boolean ifEmpty(EditText et, String st) {
        if (TextUtils.isEmpty(st)) {
            et.setError("Can't be Left Empty");
            return true;
        } else
            return false;
    }
}
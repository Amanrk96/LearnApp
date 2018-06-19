package com.example.hp.edume;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginMain extends AppCompatActivity {
    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        username = (EditText) findViewById(R.id.lusername);
        password = (EditText) findViewById(R.id.lpassword);
        login = (Button) findViewById(R.id.mbtn_login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lname = username.getText().toString();
                String lpassword = password.getText().toString();
                if (ifEmpty(username, lname) && ifEmpty(password, lpassword)) {
                    return;
                } else {

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            try {
                                JSONObject jsonResponse = new JSONObject(s);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    String name = jsonResponse.getString("name");
                                    Intent i = new Intent(LoginMain.this, UserArea.class);
                                    i.putExtra("name", name);
                                    startActivity(i);
                               /* CookieManager cookieManage = new CookieManager();
                                CookieHandler.setDefault(cookieManage);*/
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginMain.this);
                                    builder.setMessage("Login Failed!");
                                    builder.setNegativeButton("Retry", null);
                                    builder.create();
                                    builder.show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    LoginRequest loginRequest = new LoginRequest(lname, lpassword, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginMain.this);
                    queue.add(loginRequest);
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
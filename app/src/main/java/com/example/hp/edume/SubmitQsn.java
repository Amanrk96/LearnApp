package com.example.hp.edume;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SubmitQsn extends AppCompatActivity {
    EditText qsn;
    Button qsub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_qsn);

        qsn = (EditText) findViewById(R.id.et_submitqsn);
        qsub = (Button) findViewById(R.id.btn_submitqsn);
        qsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subqsn = qsn.getText().toString();
                Intent intent = getIntent();
                String name = intent.getStringExtra("name");
                Response.Listener<String> responselistener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonResponse = new JSONObject(s);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = getIntent();
                                String name = intent.getStringExtra("name");
                                Intent i = new Intent(SubmitQsn.this, UserArea.class);
                                i.putExtra("name", name);
                                SubmitQsn.this.startActivity(i);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SubmitQsn.this);
                                builder.setMessage("Failed To Upload");
                                builder.setNegativeButton("Retry", null);
                                builder.create();
                                builder.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                QsnRequest qsnRequest = new QsnRequest(subqsn, name, responselistener);
                RequestQueue queue = Volley.newRequestQueue(SubmitQsn.this);
                queue.add(qsnRequest);
            }
        });
    }
}
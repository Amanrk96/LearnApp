package com.example.hp.edume;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SubAnsActivity extends AppCompatActivity {
    TextView question;
    EditText answer;
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_ans);
        question = (TextView) findViewById(R.id.qsnforans);
        answer = (EditText) findViewById(R.id.subans);
        post = (Button) findViewById(R.id.btn_subans);

        final Intent getdata = getIntent();
        String qsn = getdata.getStringExtra("question");

        question.setText(qsn);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ans = answer.getText().toString();
                int id = getdata.getIntExtra("id", 0);
                String byname = getdata.getStringExtra("name");
                Response.Listener<String> responselistener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonResponse = new JSONObject(s);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = getIntent();
                                String name = intent.getStringExtra("name");
                                Intent i = new Intent(SubAnsActivity.this, UserArea.class);
                                i.putExtra("name", name);
                                SubAnsActivity.this.startActivity(i);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SubAnsActivity.this);
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
                AnsRequest ansRequest = new AnsRequest(ans, id , byname , responselistener);
                RequestQueue queue = Volley.newRequestQueue(SubAnsActivity.this);
                queue.add(ansRequest);
            }
        });
    }
}
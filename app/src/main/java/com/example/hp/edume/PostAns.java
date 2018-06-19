package com.example.hp.edume;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class PostAns extends AppCompatActivity {
    RecyclerView qsnrv;
    RecyclerView.LayoutManager layoutManager;
    public CustomAdapter adapter;
    public List<Qsns> qsnsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postans_rv);

        qsnrv = (RecyclerView) findViewById(R.id.postans_rv);
        qsnrv.setHasFixedSize(true);

        qsnsList = new ArrayList<>();
        fetchQsn(0);

        layoutManager = new LinearLayoutManager(this);
        qsnrv.setLayoutManager(layoutManager);

        adapter = new CustomAdapter(this, qsnsList);   //to bind data from server to recycler view
        qsnrv.setAdapter(adapter);

        qsnrv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                final LinearLayoutManager layoutManager = (LinearLayoutManager) qsnrv.getLayoutManager();
                if (layoutManager.findLastCompletelyVisibleItemPosition() == qsnsList.size() - 1) {
                    fetchQsn(qsnsList.get(qsnsList.size() - 1).getId());
                }
            }
        });

        Intent getname = getIntent();
        String name = getname.getStringExtra("name");
        Intent toadapter = new Intent(PostAns.this, CustomAdapter.class);
        toadapter.putExtra("name", name);
    }

    private void fetchQsn(final int id) {
        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new okhttp3.Request.Builder().url("https://amanrks.000webhostapp.com/Qsnfetch.php?id=" + id).build();
                try {
                    okhttp3.Response response = client.newCall(request).execute(); // At this Point the Request has been sent

                    JSONArray array = new JSONArray(response.body().string());
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        Qsns qsns = new Qsns(object.getInt("id"), object.getString("qsn"), object.getString("name"));
                        qsnsList.add(qsns);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End Of Content");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };
        task.execute(id);
    }
}
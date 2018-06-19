package com.example.hp.edume;

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

import okhttp3.OkHttpClient;

public class QnsAnsActivity extends AppCompatActivity {

    RecyclerView qsnansrv;
    RecyclerView.LayoutManager layoutManager;
    public LearnAdapter adapterLearn;
    public List<QsnAns> qsnAnsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postans_rv);

        qsnansrv = (RecyclerView) findViewById(R.id.postans_rv);
        qsnansrv.setHasFixedSize(true);

        qsnAnsList = new ArrayList<>();
        fetchQsnAns(0);

        layoutManager = new LinearLayoutManager(this);
        qsnansrv.setLayoutManager(layoutManager);

        adapterLearn = new LearnAdapter(this, qsnAnsList);
        qsnansrv.setAdapter(adapterLearn);

        qsnansrv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                final LinearLayoutManager layoutManager = (LinearLayoutManager) qsnansrv.getLayoutManager();
                if (layoutManager.findLastCompletelyVisibleItemPosition() == qsnAnsList.size() - 1) {
                    fetchQsnAns(qsnAnsList.get(qsnAnsList.size() - 1).getId());
                }
            }
        });
    }

    private void fetchQsnAns(final int id) {
        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new okhttp3.Request.Builder().url("https://amanrks.000webhostapp.com/Fetchqsnans.php?id=" + id).build();
                try {
                    okhttp3.Response response = client.newCall(request).execute(); // At this Point the Request has been sent

                    JSONArray array = new JSONArray(response.body().string());
                    qsnAnsList.clear();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        QsnAns qsnsans = new QsnAns(object.getString("qsn"), object.getString("ans"), object.getInt("id"));
                        qsnAnsList.add(qsnsans);
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
                adapterLearn.notifyDataSetChanged();
            }
        };
        task.execute(id);
    }
}
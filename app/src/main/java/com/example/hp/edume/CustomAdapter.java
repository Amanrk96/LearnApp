package com.example.hp.edume;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by HP on 27-Jun-17.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    List<Qsns> qsnsList;

    public CustomAdapter(Context context, List<Qsns> qsnsList) {
        this.context = context;
        this.qsnsList = qsnsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_postans, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) { // we can manipulate views
        holder.question.setText(qsnsList.get(position).getQsn());
        final String qsn = qsnsList.get(position).getQsn();
        final int id = qsnsList.get(position).getId();

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getname = ((Activity) context).getIntent();
                String name = getname.getStringExtra("name");

                Intent i = new Intent(view.getContext(), SubAnsActivity.class);
                i.putExtra("question", qsn);
                i.putExtra("id", id);
                i.putExtra("name", name);
                startActivity(view.getContext(), i, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return qsnsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView question;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.qsn_posting);
            linearLayout = itemView.findViewById(R.id.linearlayoutpostans);
        }
    }
}
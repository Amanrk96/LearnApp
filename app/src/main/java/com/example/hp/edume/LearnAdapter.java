package com.example.hp.edume;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP on 19-Jul-17.
 */

class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.ViewHolder> {
    Context context;
    List<QsnAns> qsnsAnsList;

    public LearnAdapter(Context context, List<QsnAns> qsnsAnsList) {
        this.context = context;
        this.qsnsAnsList = qsnsAnsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.qsnans_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.question.setText(qsnsAnsList.get(position).getQsn());
        holder.answer.setText(qsnsAnsList.get(position).getAns());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return qsnsAnsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView question, answer;

        public ViewHolder(View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.qsnlearn);
            answer = itemView.findViewById(R.id.anslearn);
        }
    }
}
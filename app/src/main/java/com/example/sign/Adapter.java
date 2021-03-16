package com.example.sign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyView> {

    private ArrayList<Formation> list;
    private int res;
    private clickListener ClickListener;

    Adapter(ArrayList<Formation> list, int res, clickListener ClickListener){
        this.res = res;
        this.list = list;
        this.ClickListener = ClickListener;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(
                res,
                parent,
                false
        );
        return new MyView(view, ClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        Formation f = list.get(position);
        holder.title.setText(f.getTitre());
        holder.description.setText("Lieu : " + f.getLieu());
        holder.date.setText(f.getDate_d());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyView extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, description, date;
        clickListener clickListen;

        public MyView(@NonNull View itemView, clickListener n) {
            super(itemView);

            title = itemView.findViewById(R.id.titleC);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            clickListen = n;

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            clickListen.onClickListener(getAdapterPosition());
        }
    }

    public interface clickListener{
        void onClickListener(int position);
    }
}

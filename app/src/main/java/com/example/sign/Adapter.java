package com.example.sign;

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

    Adapter(ArrayList<Formation> list, int res){
        this.res = res;
        this.list = list;
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
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        Formation f = list.get(position);
        holder.title.setText(f.getTitre());
        holder.description.setText(f.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyView extends RecyclerView.ViewHolder{
        TextView title, description;


        public MyView(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleC);
            description = itemView.findViewById(R.id.description);
        }
    }
}

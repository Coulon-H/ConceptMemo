package com.example.sign;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements Adapter.clickListener{


    private Adapter a;
    private ArrayList<Formation> list = new ArrayList<>();
    private View.OnClickListener click, click2;

    public HomeFragment(View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        a = new Adapter(list, R.layout.model_bloc, this);
        click = onClickListener;
        click2 = onClickListener2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Button cnfl, auf;
        cnfl = v.findViewById(R.id.cnfl);
        auf = v.findViewById(R.id.auf);

        cnfl.setOnClickListener(click);
        auf.setOnClickListener(click2);

        return v;
    }


    @Override
    public void onClickListener(int position) {

    }
}
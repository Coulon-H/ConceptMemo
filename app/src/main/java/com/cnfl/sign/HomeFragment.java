package com.cnfl.sign;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements Adapter.clickListener{


    private Adapter a;
    private ArrayList<Formation> list = new ArrayList<>();
    private View.OnClickListener click, click2, click3;

    public HomeFragment(View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnClickListener onClickListener3) {
        a = new Adapter(list, R.layout.model_bloc, this);
        click = onClickListener;
        click2 = onClickListener2;
        click3 = onClickListener3;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Button cnfl, auf, cef;
        cnfl = v.findViewById(R.id.cnfl);
        auf = v.findViewById(R.id.auf);
        cef = v.findViewById(R.id.cn);

        cnfl.setOnClickListener(click);
        auf.setOnClickListener(click2);
        cef.setOnClickListener(click3);

        return v;
    }


    @Override
    public void onClickListener(int position) {

    }
}
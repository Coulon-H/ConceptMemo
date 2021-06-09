package com.cnfl.sign;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
        TextView t2, t4, t;
        cnfl = v.findViewById(R.id.cnfl);
        auf = v.findViewById(R.id.auf);
        cef = v.findViewById(R.id.cn);
        t2 = v.findViewById(R.id.textView2);
        t4 = v.findViewById(R.id.textView4);
        t = v.findViewById(R.id.textView);

        cnfl.setOnClickListener(click);
        auf.setOnClickListener(click2);
        cef.setOnClickListener(click3);
        t.setOnClickListener(v12 -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:01444509"));
            startActivity(intent);
        });
        t4.setOnClickListener(v13 -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:01444517"));
            startActivity(intent);
        });
        t2.setOnClickListener(v1 -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            //intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"bomyron891@gmail.com"});
            //intent.putExtra(Intent.EXTRA_EMAIL, "bomyron891@gmail.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Sujet ici ...");
            intent.setType("message/rfc822");
            startActivity(intent);
        });



        return v;
    }



    @Override
    public void onClickListener(int position) {

    }
}
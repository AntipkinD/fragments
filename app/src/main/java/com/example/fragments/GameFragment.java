package com.example.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class GameFragment extends Fragment {

    final String LOG_TAG = "myLogs";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, null);

        Button button = (Button) v.findViewById(R.id.red_button);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                //((Button)getActivity().findViewById(R.id.btnFind)).setText(MainActivity.money.toString());
            }
        });

        return v;
    }
}
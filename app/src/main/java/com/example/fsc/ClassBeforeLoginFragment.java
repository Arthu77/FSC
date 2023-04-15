package com.example.fsc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class ClassBeforeLoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_a_c_n_before_login, null);

        Button go_to_login_btn=(Button) view.findViewById(R.id.go_to_login_btn);
        go_to_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainBeforeLoginActivity mainBeforeLoginActivity= (MainBeforeLoginActivity) getActivity();
                mainBeforeLoginActivity.setTab(4);
            }
        });

        return view;
    }
}
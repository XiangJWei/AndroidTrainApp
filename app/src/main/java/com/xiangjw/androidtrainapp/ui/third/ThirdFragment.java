package com.xiangjw.androidtrainapp.ui.third;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.xiangjw.androidtrainapp.R;

public class ThirdFragment extends Fragment {

    private ThirdViewModel thirdViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        thirdViewModel =
                ViewModelProviders.of(this).get(ThirdViewModel.class);
        View root = inflater.inflate(R.layout.fragment_third, container, false);
        final TextView textView = root.findViewById(R.id.text_third);
        thirdViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
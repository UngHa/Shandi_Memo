package com.example.shandi_memo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//user_information Fragment 처리 YCK
public class CalenderManagement extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.calendar_management, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView){

    }
}

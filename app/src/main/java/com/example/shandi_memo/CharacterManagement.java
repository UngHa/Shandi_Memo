package com.example.shandi_memo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

//MatchingList Fragment 처리 YCK
public class CharacterManagement extends Fragment {
    RecyclerView matchList;
    MatchListAdapter adapter;

    Context context;
    OnTapItemSelectedListener listener;

    Dialog filterDialog;

    public void onAttach(Context context){
        super.onAttach(context);

        this.context=context;

        if(context instanceof OnTapItemSelectedListener){
            listener = (OnTapItemSelectedListener) context;
        }
    }

    public void onDetach(){
        super.onDetach();

        if(context != null){
            context = null;
            listener = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.character_management, container, false);
        return rootView;
    }


}

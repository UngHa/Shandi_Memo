package com.example.shandi_memo;

import android.view.View;

//네비게이션바 클릭 시 리스너
public interface OnTapItemSelectedListener {
    void onItemClick(PlanListAdapter.ViewHolder holder, View view, int position);
    void onItemClick(CharacterListAdapter.ViewHolder holder, View view, int position);
}

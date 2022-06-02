package com.example.shandi_memo;

import android.view.View;

//네비게이션바 클릭 시 리스너 YCK
public interface OnTapItemSelectedListener {
    public void onItemClick(MatchListAdapter.ViewHolder holder, View view, int position);
}

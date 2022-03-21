package com.example.newsapp.ui;

import com.example.newsapp.ui.notifications.NewModel;

public interface OnItemClickListener {
    void OnItemClick(int position);
    void OnItemLongClickListener(int position, NewModel newModel);
}

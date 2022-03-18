package com.example.newsapp.ui;

import java.io.Serializable;

public class NewModel implements Serializable {
    private String textTitle;
    private  long CreateAt;

    public NewModel(String textTitle, long createAt) {
        this.textTitle = textTitle;
        CreateAt = createAt;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public long getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(long createAt) {
        CreateAt = createAt;
    }
}

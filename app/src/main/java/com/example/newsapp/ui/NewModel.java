package com.example.newsapp.ui;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class NewModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String textTitle;
    private long createAt;

    public NewModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  NewModel(String textTitle, long createAt) {
        this.textTitle = textTitle;
        this.createAt = createAt;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }
}

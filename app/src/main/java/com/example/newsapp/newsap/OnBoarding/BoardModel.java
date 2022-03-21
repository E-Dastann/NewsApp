package com.example.newsapp.newsap.OnBoarding;

import android.widget.Button;

import java.util.ArrayList;

public class BoardModel {
    private String title;
    private String desc;
    private Integer image ;

    private ArrayList<BoardModel>list;

    public BoardModel(String title, String desc, Integer image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}

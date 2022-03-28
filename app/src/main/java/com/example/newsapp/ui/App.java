package com.example.newsapp.ui;

import android.app.Application;

import androidx.room.Room;

import com.example.newsapp.room.AppDataBase;

public class App extends Application {
    private static AppDataBase dataBase;
    @Override
    public void onCreate() {
        super.onCreate();
        dataBase= Room.databaseBuilder(this,AppDataBase.class,"database").
                allowMainThreadQueries().fallbackToDestructiveMigration().build();

    }

    public static AppDataBase getDataBase() {
        return dataBase;
    }
}

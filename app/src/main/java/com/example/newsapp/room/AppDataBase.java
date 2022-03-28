package com.example.newsapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.newsapp.ui.NewModel;

@Database(entities = {NewModel.class},version = 3)
public abstract class AppDataBase extends RoomDatabase {
    public abstract NewDao newDao ();

}

package com.example.newsapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.newsapp.ui.NewModel;

import java.util.List;

@Dao
public interface NewDao {

    @Query("SELECT * FROM newmodel ORDER by createAt DESC")
    List<NewModel> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(NewModel newModel);

    @Delete
    void delete(NewModel newModel);


    @Query("SELECT * FROM newmodel ORDER by textTitle ASC")
    List<NewModel> sort();

    @Query("SELECT * FROM newmodel WHERE textTitle LIKE '%' || :search || '%' ")
    List<NewModel> findUserWithName(String search);


}

package com.example.shubhamverma.newsfeeddemo.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.shubhamverma.newsfeeddemo.model.NewsFeedModel;

import java.util.List;

@Dao
public interface NewsFeedDao {

    @Query("SELECT * FROM NEWS_FEED")
    List<NewsFeedModel> getAll();


    @Query(value = "SELECT * FROM NEWS_FEED WHERE DATE_TIME LIKE :date")
    List<NewsFeedModel> getDataWithDate(String date);

    @Insert
    void insertTask(NewsFeedModel newsFeedModel);

    @Insert
    void insertAll(NewsFeedModel... newsFeed);

    @Insert
    void insert(NewsFeedModel newsFeed);

    @Delete
    void delete(NewsFeedModel newsFeed);
}

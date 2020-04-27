package com.example.shopapp.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM item")
    List<Item> getAll();

    @Query("SELECT * FROM item WHERE id IN (:itemIds)")
    List<Item> loadAllByIds(int[] itemIds);

    @Query("SELECT * FROM item WHERE title LIKE :title")
    Item findByTitle(String title);

    @Query("SELECT * FROM item WHERE id LIKE :id")
    Item findById(int id);

    @Insert
    void insertAll(Item... items);

    @Delete
    void delete(Item item);
}

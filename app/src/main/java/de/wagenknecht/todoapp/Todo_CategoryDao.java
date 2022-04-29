package de.wagenknecht.todoapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface Todo_CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTodo_Category(Todo_Category todo_category);
}

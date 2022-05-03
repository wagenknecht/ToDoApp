package de.wagenknecht.todoapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import de.wagenknecht.todoapp.entity.Todo_Category;

@Dao
public interface Todo_CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTodo_Category(Todo_Category todo_category);
}

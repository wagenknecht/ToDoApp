package de.wagenknecht.todoapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.wagenknecht.todoapp.entity.Todo;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTodo(Todo todo);

    @Query("select * from todo")
    List<Todo> getAllTodo();

    @Query("select * from todo where todo_id = :todoId")
    Todo getTodo(int todoId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTodo(Todo todo);

    @Query("delete from todo")
    void removeAllTodos();

    @Query("delete from todo where todo_id = :todoId")
    void removeTodo(int todoId);
}

package de.wagenknecht.todoapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import de.wagenknecht.todoapp.entity.Todo;
import de.wagenknecht.todoapp.entity.relations.TodoWithCategories;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTodo(Todo todo);

    @Query("select * from todo")
    List<Todo> getAllTodo();

    @Query("select priority_id from todo")
    List<Integer> getAllPriorityIdFromTodo();

    @Query("select * from todo where todo_id = :todoId")
    Todo getTodo(int todoId);

    @Query("select * from todo order by todo_id desc limit 1")
    Todo getLastTodo();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTodo(Todo todo);

    @Query("delete from todo")
    void removeAllTodos();

    @Query("delete from todo where todo_id = :todoId")
    void removeTodo(int todoId);


    @Transaction
    @Query("select * from todo")
    public List<TodoWithCategories> getTodoWithCategories();

    @Transaction
    @Query("select * from todo where todo_id = :todo_id")
    public TodoWithCategories getTodoWithCategories(int todo_id);
}

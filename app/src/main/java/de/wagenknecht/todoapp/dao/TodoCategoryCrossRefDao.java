package de.wagenknecht.todoapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import de.wagenknecht.todoapp.entity.Todo;
import de.wagenknecht.todoapp.entity.relations.TodoCategoryCrossRef;

@Dao
public interface TodoCategoryCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTodoCategoryCrossRef(TodoCategoryCrossRef todoCategoryCrossRef);

    @Query("select category_id from todocategorycrossref where todo_id = :todo_id")
    List<Integer> getAllCategoriesByTodoId(int todo_id);

    @Query("delete from todocategorycrossref where todo_id = :todo_id")
    void deleteAllTodoCategoryCrossRefByTodoId(int todo_id);
}

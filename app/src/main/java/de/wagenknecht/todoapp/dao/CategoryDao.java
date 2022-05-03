package de.wagenknecht.todoapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.wagenknecht.todoapp.entity.Category;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCategory(Category category);

    @Query("select * from category")
    List<Category> getAllCategories();

//    @Query("select * from todo where todo_id = :todoId")
//    public List<Todo> getTodo(long todoId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCategory(Category category);

    @Query("delete from category")
    void removeAllCategories();
}

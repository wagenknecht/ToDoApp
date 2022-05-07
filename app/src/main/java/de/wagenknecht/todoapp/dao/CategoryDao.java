package de.wagenknecht.todoapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import de.wagenknecht.todoapp.entity.Category;
import de.wagenknecht.todoapp.entity.Todo;
import de.wagenknecht.todoapp.entity.relations.CategoryWithTodos;
import de.wagenknecht.todoapp.entity.relations.TodoCategoryCrossRef;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTodo(Todo todo);

    @Query("select * from category")
    List<Category> getAllCategories();

    @Query("select category_id from category")
    List<Integer> getAllCategoryIds();

    @Query("select category_name from category")
    List<String> getAllCategoryNames();

    @Query("select * from category where category_id = :category_id")
    public Category getCategory(int category_id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCategory(Category category);

    @Query("delete from category")
    void removeAllCategories();

    @Query("delete from category where category_id = :category_id")
    void removeCategory(int category_id);

    @Transaction
    @Query("select * from category")
    public List<CategoryWithTodos> getCategoryWithTodos();

    @Transaction
    @Query("select * from category where category_id = :category_id")
    public List<CategoryWithTodos> getCategoryWithTodos(int category_id);

}

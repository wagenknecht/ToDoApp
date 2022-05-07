package de.wagenknecht.todoapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import de.wagenknecht.todoapp.entity.relations.TodoCategoryCrossRef;

@Dao
public interface TodoCategoryCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTodoCategoryCrossRef(TodoCategoryCrossRef todoCategoryCrossRef);
}

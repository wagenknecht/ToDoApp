package de.wagenknecht.todoapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.wagenknecht.todoapp.entity.Priority;
import de.wagenknecht.todoapp.entity.Todo;

@Dao
public interface PriorityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPriority(Priority priority);

    @Query("select * from priority")
    List<Priority> getAllPriorities();

    @Query("select priority_id from priority")
    List<Integer> getAllPriorityIds();

    @Query("select * from priority where priority_id = :priority_id")
    Priority getPriority(int priority_id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePriority(Priority priority);

    @Query("delete from priority")
    void removeAllPriorities();

    @Query("delete from priority where priority_id = :priority_id")
    void removePriority(int priority_id);
}

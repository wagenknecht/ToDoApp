package de.wagenknecht.todoapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PriorityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPriority(Priority priority);

    @Query("select * from priority")
    List<Priority> getAllPriorities();

    @Query("select * from todo where priority_id = :priority_id")
    List<Todo> getPriority(long priority_id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePriority(Priority priority);

    @Query("delete from priority")
    void removeAllPriorities();

    @Query("delete from priority where priority_id = :priority_id")
    void removePriority(long priority_id);
}

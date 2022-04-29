package de.wagenknecht.todoapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"todo_id", "category_id"})
public class Todo_Category {

    @PrimaryKey
    public long todo_id;
    @PrimaryKey
    public long category_id;

    public Todo_Category(long todo_id, long category_id) {
        this.todo_id = todo_id;
        this.category_id = category_id;
    }
}

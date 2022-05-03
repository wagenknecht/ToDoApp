package de.wagenknecht.todoapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"todo_id", "category_id"})
public class Todo_Category {

    @PrimaryKey
    public int todo_id;
    @PrimaryKey
    public int category_id;

    public Todo_Category(int todo_id, int category_id) {
        this.todo_id = todo_id;
        this.category_id = category_id;
    }
}

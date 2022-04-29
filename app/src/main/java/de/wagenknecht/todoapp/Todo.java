package de.wagenknecht.todoapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {

    @PrimaryKey(autoGenerate = true)
    public long todo_id;
    public String title;
    public String description;
    public long priority_id;
    public Priority priority;

    public Todo(String title, String description, Priority priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    enum Priority{
        HIGH,
        MEDIUM,
        LOW;
    }
}
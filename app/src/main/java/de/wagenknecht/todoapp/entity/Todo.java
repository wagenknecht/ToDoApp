package de.wagenknecht.todoapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {

    @PrimaryKey(autoGenerate = true)
    public int todo_id;

    @ColumnInfo
    public String title;
    public String description;
    public long priority_id;
//    public Priority priority;

    public Todo() {
    }

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;

    }

//    enum Priority{
//        HIGH,
//        MEDIUM,
//        LOW
//    }
}
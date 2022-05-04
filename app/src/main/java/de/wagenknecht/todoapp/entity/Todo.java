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
    public int priority_id;
    public String datetime;
//    public Priority priority;

    public Todo() {
    }



//    enum Priority{
//        HIGH,
//        MEDIUM,
//        LOW
//    }
}
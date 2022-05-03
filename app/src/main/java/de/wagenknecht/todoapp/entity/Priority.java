package de.wagenknecht.todoapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Priority {

    @PrimaryKey(autoGenerate = true)
    public int priority_id;
    public String priority_name;

    public Priority() {
    }

    public Priority(String priority_name) {
        this.priority_name = priority_name;
    }
}


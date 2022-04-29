package de.wagenknecht.todoapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey(autoGenerate = true)
    public long category_id;
    public String name;
}

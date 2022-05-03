package de.wagenknecht.todoapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey(autoGenerate = true)
    public long category_id;
    public String name;
}

package de.wagenknecht.todoapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey(autoGenerate = true)
    public int category_id;
    public String category_name;

    public Category() {
    }

    public Category(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return category_name;
    }
}

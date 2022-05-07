package de.wagenknecht.todoapp.entity.relations;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"todo_id", "category_id"})
public class TodoCategoryCrossRef {

    public int todo_id;
    public int category_id;

    public TodoCategoryCrossRef(int todo_id, int category_id) {
        this.todo_id = todo_id;
        this.category_id = category_id;
    }
}

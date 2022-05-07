package de.wagenknecht.todoapp.entity.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import de.wagenknecht.todoapp.entity.Category;
import de.wagenknecht.todoapp.entity.Todo;

public class TodoWithCategories {
    @Embedded
    public Todo todo;
    @Relation(
            parentColumn = "todo_id",
            entityColumn = "category_id",
            associateBy = @Junction(TodoCategoryCrossRef.class)
    )
    public List<Category> categories;
}

package de.wagenknecht.todoapp.entity.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import de.wagenknecht.todoapp.entity.Category;
import de.wagenknecht.todoapp.entity.Todo;


public class CategoryWithTodos {
    @Embedded public Category category;
    @Relation(
            parentColumn = "category_id",
            entityColumn = "todo_id",
            associateBy = @Junction(TodoCategoryCrossRef.class)
    )
    public List<Todo> todos;
}

package de.wagenknecht.todoapp.entity.relations;


import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import de.wagenknecht.todoapp.entity.Priority;
import de.wagenknecht.todoapp.entity.Todo;

public class PriorityWithToDos {
    @Embedded
    public Priority priority;
    @Relation(
        parentColumn = "priority_id",
        entityColumn = "priority_id"
    )
    public List<Todo> todos;
}

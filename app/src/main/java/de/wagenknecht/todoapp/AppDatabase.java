package de.wagenknecht.todoapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import de.wagenknecht.todoapp.dao.CategoryDao;
import de.wagenknecht.todoapp.dao.PriorityDao;
import de.wagenknecht.todoapp.dao.TodoDao;
import de.wagenknecht.todoapp.entity.Category;
import de.wagenknecht.todoapp.entity.Priority;
import de.wagenknecht.todoapp.entity.Todo;
import de.wagenknecht.todoapp.entity.relations.TodoCategoryCrossRef;

@Database(entities = {
                Todo.class,
                Category.class,
                Priority.class,
                TodoCategoryCrossRef.class
}, version = 18, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract TodoDao todoDao();
    public abstract CategoryDao categoryDao();
    public abstract PriorityDao priorityDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, "TodoDB")
                            //Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // To simplify the exercise, allow queries on the main thread.
                            // Don't do this on a real app!
                            .allowMainThreadQueries()
                            // recreate the database if necessary
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}

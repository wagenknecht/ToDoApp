package de.wagenknecht.todoapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import de.wagenknecht.todoapp.adapter.TodoListAdapter;
import de.wagenknecht.todoapp.entity.Todo;

public class MainActivity extends AppCompatActivity implements TodoListAdapter.onTodoListener {

    private TodoListAdapter todoListAdapter;
    private List<Todo> todoList = new ArrayList<>();
    public AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        loadTodos();
    }

    private void initRecyclerView() {
        //Anzeige der Item Liste
        RecyclerView recyclerView = findViewById(R.id.todoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoListAdapter = new TodoListAdapter(this, this);
        recyclerView.setAdapter(todoListAdapter);

        //Hier fügen wir einen TouchCallback ein den wir an den RecyclerView binden
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback(todoListAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void loadTodos(){
        database = AppDatabase.getDatabase(getApplicationContext());
        todoList = database.todoDao().getAllTodo();
        todoListAdapter.setTodoList(todoList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTodos();
    }

    public void showDetailActivity(View view) {
        Intent intent = new Intent(this, TodoActivity.class);
        startActivity(intent);
    }

    //Kontextmenu
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.MenuNewTodo:
                Intent intent_detail = new Intent(this, TodoActivity.class);
                startActivity(intent_detail);
                return true;
            case R.id.MenuNewPriority:
                Intent intent_priority = new Intent(this, PriorityActivity.class);
                startActivity(intent_priority);
                return true;
            case R.id.MenuNewCategory:
                Intent intent_category = new Intent(this, CategoryActivity.class);
                startActivity(intent_category);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    //Methode, um Klick auf ein Todoo zu handeln
    @Override
    public void onTodoClick(int position) {
        Log.d(TAG, "onTodoClick: click.");

        Intent intent = new Intent(this, TodoActivity.class);
        intent.putExtra("todoId", todoList.get(position).todo_id);
        startActivity(intent);
    }
}
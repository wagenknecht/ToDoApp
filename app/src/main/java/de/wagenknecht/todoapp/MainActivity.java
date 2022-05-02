package de.wagenknecht.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TodoListAdapter todoListAdapter;

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
        todoListAdapter = new TodoListAdapter(this);
        recyclerView.setAdapter(todoListAdapter);

        //Hier fügen wir einen TouchCallback ein den wir an den RecyclerView binden
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback(todoListAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void loadTodos(){
        AppDatabase database = AppDatabase.getDatabase(getApplicationContext());
        List<Todo> todoList = database.todoDao().getAllTodo();
        todoListAdapter.setTodoList(todoList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTodos();
    }

    public void showDetailActivity(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
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
            case R.id.newTodo:
                Intent intent_detail = new Intent(this, DetailActivity.class);
                startActivity(intent_detail);
                return true;
            case R.id.newPriority:
                Intent intent_priority = new Intent(this, PriorityActivity.class);
                startActivity(intent_priority);
                return true;
            case R.id.newCategory:

                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
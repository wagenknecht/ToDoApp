package de.wagenknecht.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;

    List<String> items = new LinkedList<>();
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = AppDatabase.getDatabase(getApplicationContext());
        update();


        items.add("Code it");

        //Anzeige der Item Liste
        RecyclerView recyclerView = findViewById(R.id.itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(items);
        recyclerView.setAdapter(adapter);

        //Hier fügen wir einen TouchCallback ein den wir an den RecyclerView binden
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback((Adapter) adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    private void update(){
        List<Todo> todoList = database.todoDao().getAllTodo();
        TextView textView = findViewById(R.id.textAnzeige);
        String ausgabe = "";
        for (Todo todo : todoList) ausgabe = ausgabe + todo.title + "; ";
        textView.setText(ausgabe);
    }

    public void pushButton(View view) {

        update();
    }

    public void pushPlus(View view) {
        TextView eingabe = findViewById(R.id.textEingabe);
        items.add(eingabe.getText().toString());
        adapter.notifyItemInserted(items.size()-1);
//        database.todoDao().addTodo(new Todo("Arbeit", "", Todo.Priority.HIGH));
//        update();
    }


}
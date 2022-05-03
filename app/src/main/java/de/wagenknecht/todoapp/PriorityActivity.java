package de.wagenknecht.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

import de.wagenknecht.todoapp.adapter.PriorityListAdapter;
import de.wagenknecht.todoapp.entity.Priority;

public class PriorityActivity extends AppCompatActivity {

    private PriorityListAdapter priorityListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority);
        final EditText inputNewPriority = findViewById(R.id.newPriority);
        ImageButton addNewPriority = findViewById(R.id.buttonAddNewPriority);
        addNewPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePriority(inputNewPriority.getText().toString());
                inputNewPriority.setText("");
            }
        });
        initRecyclerView();
        loadPriorities();
    }

    private void savePriority(String priorityName) {
        AppDatabase database = AppDatabase.getDatabase(this.getApplicationContext());
        database.priorityDao().addPriority(new Priority(priorityName));
        loadPriorities();
    }

    private void initRecyclerView() {
        //Anzeige der Item Liste
        RecyclerView recyclerView = findViewById(R.id.priorityList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        priorityListAdapter = new PriorityListAdapter(this);
        recyclerView.setAdapter(priorityListAdapter);
    }

    private void loadPriorities() {
        AppDatabase database = AppDatabase.getDatabase(this.getApplicationContext());
        List<Priority> priorityList = database.priorityDao().getAllPriorities();
        priorityListAdapter.setPriorityList(priorityList);
    }
}
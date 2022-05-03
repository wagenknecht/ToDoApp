package de.wagenknecht.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import de.wagenknecht.todoapp.adapter.PriorityListAdapter;
import de.wagenknecht.todoapp.entity.Priority;

public class PriorityActivity extends AppCompatActivity {

    PriorityListAdapter priorityListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority);
        final EditText inputNewPriority = findViewById(R.id.inputNewPriority);
        Button saveNewPriority = findViewById(R.id.buttonSaveNewPriority);
        saveNewPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePriority(inputNewPriority.getText().toString());
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

        //Hier fügen wir einen TouchCallback ein den wir an den RecyclerView binden
//        ItemTouchHelper itemTouchHelper = new
//                ItemTouchHelper(new SwipeToDeleteCallback(priorityListAdapter));
//        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void loadPriorities() {
        AppDatabase database = AppDatabase.getDatabase(this.getApplicationContext());
        List<Priority> priorityList = database.priorityDao().getAllPriorities();
        priorityListAdapter.setPriorityList(priorityList);
    }

//    private void initPriorityList(){
//        TextView textview = findViewById(R.id.testPriority);
//        String helper = "";
//        for(Priority p : loadPriorities()){
//           helper = helper + p.priority_id + ": " + p.priority_name + "\n";
//        }
//        textview.setText(helper);
//    }


}
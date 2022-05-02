package de.wagenknecht.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class PriorityActivity extends AppCompatActivity {

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
        loadPriorities();
        initPriorityList();
    }

    private void savePriority(String priorityName) {
        AppDatabase database = AppDatabase.getDatabase(this.getApplicationContext());
        database.priorityDao().addPriority(new Priority(priorityName));
        initPriorityList();
    }

    private List<Priority> loadPriorities() {
        AppDatabase database = AppDatabase.getDatabase(this.getApplicationContext());
        List<Priority> priorityList = database.priorityDao().getAllPriorities();
        return priorityList;
    }

    private void initPriorityList(){
        TextView textview = findViewById(R.id.testPriority);
        String helper = "";
        for(Priority p : loadPriorities()){
           helper = helper + p.priority_id + ": " + p.priority_name + "\n";
        }
        textview.setText(helper);
    }


}
package de.wagenknecht.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    }

    private void savePriority(String priorityName) {
        AppDatabase database = AppDatabase.getDatabase(this.getApplicationContext());
        database.priorityDao().addPriority(new Priority(priorityName));
    }
}
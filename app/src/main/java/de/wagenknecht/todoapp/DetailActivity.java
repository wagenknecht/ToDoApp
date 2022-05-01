package de.wagenknecht.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final EditText inputTitel = findViewById(R.id.inputTitel);
        final EditText inputBeschreibung = findViewById(R.id.inputBeschreibung);
        EditText inputAblaufdatum = findViewById(R.id.inputAblaufdatum);
        Spinner inputPriorität = findViewById(R.id.inputPriorität);
        Button saveTodo = findViewById(R.id.saveToDo);
        saveTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTodo(inputTitel.getText().toString(), inputBeschreibung.getText().toString());
            }
        });

    }

    public void saveTodo(String titel, String beschreibung) {
        AppDatabase database = AppDatabase.getDatabase(this.getApplicationContext());
        Todo newTodo = new Todo();
        newTodo.title = titel;
        newTodo.description = beschreibung;
        database.todoDao().addTodo(newTodo);

        finish();
    }
}
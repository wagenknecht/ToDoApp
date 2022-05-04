package de.wagenknecht.todoapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import de.wagenknecht.todoapp.entity.Todo;

public class TodoActivity extends AppCompatActivity {

    Todo todo;
    AppDatabase database;


    EditText inputAblaufdatum;
    //Date Picker Dialog
    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        final EditText inputTitel = findViewById(R.id.inputTitel);
        final EditText inputBeschreibung = findViewById(R.id.inputBeschreibung);
        int todoId = getIntent().getIntExtra("todoId", -1);
        database = AppDatabase.getDatabase(getApplicationContext());
        if(todoId != -1){
            todo = database.todoDao().getTodo(todoId);
            inputTitel.setText(todo.title);
            inputBeschreibung.setText(todo.description);
            inputAblaufdatum = findViewById(R.id.inputAblaufdatum);
            inputAblaufdatum.setText(todo.datetime);
        } else {
            todo = new Todo();
        }

        //Datumsauswahl
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        inputAblaufdatum = findViewById(R.id.inputAblaufdatum);
        inputAblaufdatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TodoActivity.this, com.google.android.material.R.style.Theme_AppCompat_Light_Dialog_MinWidth,
                        onDateSetListener, year, month, day);
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();

            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day+"."+month+"."+year;
                inputAblaufdatum.setText(date);
            }
        };


        //Auswahl Priorität
//        Spinner inputPriorität = findViewById(R.id.inputPriorität);
//        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        inputPriorität.setAdapter(spinnerAdapter);
//
//        spinnerAdapter.addAll(database.priorityDao().getAllPriorities().get(0).priority_name);
//        spinnerAdapter.notifyDataSetChanged();

        //Buttons
        Button saveTodo = findViewById(R.id.saveToDo);
        saveTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTodo(inputTitel.getText().toString(), inputBeschreibung.getText().toString(), inputAblaufdatum.getText().toString());
            }
        });
        Button deleteTodo = findViewById(R.id.deleteTodo);
        deleteTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTodo();
            }
        });

    }

    public void saveTodo(String titel, String beschreibung, String datum) {

        if(titel.isEmpty()){
            todo.title = "Todo";
        } else {
            todo.title = titel;
        }

        todo.description = beschreibung;
        todo.datetime = datum;
        database.todoDao().addTodo(todo);

        finish();
    }

    private void deleteTodo() {
        database.todoDao().removeTodo(todo.todo_id);
        finish();
    }

}
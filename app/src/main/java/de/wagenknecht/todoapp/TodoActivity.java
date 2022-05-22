package de.wagenknecht.todoapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import de.wagenknecht.todoapp.entity.Category;
import de.wagenknecht.todoapp.entity.Priority;
import de.wagenknecht.todoapp.entity.Todo;
import de.wagenknecht.todoapp.entity.relations.TodoCategoryCrossRef;
import de.wagenknecht.todoapp.entity.relations.TodoWithCategories;

public class TodoActivity extends AppCompatActivity {

    Todo todo;
    AppDatabase database;


    EditText inputAblaufdatum;
    //Date Picker Dialog
    DatePickerDialog.OnDateSetListener onDateSetListener;


    //Kategorien auswählen
    ArrayList<Integer> categoryList;
    String[] categoryArray;
    Integer[] categoryIds;
    boolean[] selectCategoriesArray;
    TextView selectCategories;


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

            selectCategories = findViewById(R.id.selectCategories);
            List<Category> categoryList = database.todoDao().getTodoWithCategories(todo.todo_id).categories;
            String kategorien = "";
            for(Category c : categoryList){
                kategorien += c.category_name + ", ";
            }
            selectCategories.setText(kategorien);
        } else {
            todo = new Todo();
            Log.d(TAG, "Hier:" + String.valueOf(todo.todo_id));
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
        List<Priority> priorityList = database.priorityDao().getAllPriorities();
        Spinner prioritySpinner = findViewById(R.id.inputPriorität);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, priorityList);

        prioritySpinner.setAdapter(spinnerAdapter);

        //Postion des Spinners mit vorh
        prioritySpinner.setSelection(database.priorityDao().getAllPriorityIds().indexOf(todo.priority_id));
        prioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Priority priority = (Priority) adapterView.getSelectedItem();
                todo.priority_id = priority.priority_id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);





        spinnerAdapter.addAll();
        spinnerAdapter.notifyDataSetChanged();

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

        //Kategorien auswählen
        //Genutzt: https://www.geeksforgeeks.org/how-to-implement-multiselect-dropdown-in-android/
        selectCategories = findViewById(R.id.selectCategories);
        categoryList = new ArrayList<>();
        categoryArray = database.categoryDao().getAllCategoryNames().toArray(new String[0]);
        categoryIds = database.categoryDao().getAllCategoryIds().toArray(new Integer[0]);
        selectCategoriesArray = new boolean[categoryArray.length];


        selectCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.todoCategoryCrossRefDao().deleteAllTodoCategoryCrossRefByTodoId(todo.todo_id);
                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(TodoActivity.this);

                // set title
                builder.setTitle("Kategorie wählen");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(categoryArray, selectCategoriesArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            categoryList.add(i);
                            // Sort array list
                            Collections.sort(categoryList);
                        } else {
                            // when checkbox unselected
                            // Remove position from categoryList
                            categoryList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < categoryList.size(); j++) {
                            // concat array value
                            //database.todoCategoryCrossRefDao().insertTodoCategoryCrossRef(new TodoCategoryCrossRef(todoId, categoryIds[categoryList.get(j)]));
                            stringBuilder.append(categoryArray[categoryList.get(j)]);
                            // check condition
                            if (j != categoryList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        selectCategories.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Alle entfernen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectCategoriesArray.length; j++) {
                            // remove all selection
                            selectCategoriesArray[j] = false;
                            // clear language list
                            categoryList.clear();
                            // clear text view value
                            selectCategories.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
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

        //Erneut aufrufen, da nun ID Vergeben
        if(todo.todo_id == 0) {
            todo = database.todoDao().getLastTodo();
        }

        for (int j = 0; j < categoryList.size(); j++){

            database.todoCategoryCrossRefDao().insertTodoCategoryCrossRef(new TodoCategoryCrossRef(todo.todo_id, categoryIds[categoryList.get(j)]));
        }

        finish();
    }

    private void deleteTodo() {
        database.todoCategoryCrossRefDao().deleteAllTodoCategoryCrossRefByTodoId(todo.todo_id);
        database.todoDao().removeTodo(todo.todo_id);
        finish();
    }

}
package de.wagenknecht.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

import de.wagenknecht.todoapp.adapter.CategoryListAdapter;
import de.wagenknecht.todoapp.entity.Category;

public class CategoryActivity extends AppCompatActivity {

    private CategoryListAdapter categoryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        final EditText inputNewCategory = findViewById(R.id.newCategroy);
        ImageButton addNewCategory = findViewById(R.id.buttonAddNewCategory);
        addNewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCategory(inputNewCategory.getText().toString());
                inputNewCategory.setText("");
            }

        });

        initRecyclerView();
        loadCategories();
    }

    private void saveCategory(String categoryName) {
        AppDatabase database = AppDatabase.getDatabase(this.getApplicationContext());
        database.categoryDao().insertCategory(new Category(categoryName));
        loadCategories();
    }

    private void initRecyclerView() {
        //Anzeige der Item Liste
        RecyclerView recyclerView = findViewById(R.id.categoryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryListAdapter = new CategoryListAdapter(this);
        recyclerView.setAdapter(categoryListAdapter);
    }

    private void loadCategories() {
        AppDatabase database = AppDatabase.getDatabase(this.getApplicationContext());
        List<Category> categoryList = database.categoryDao().getAllCategories();
        categoryListAdapter.setCategoryList(categoryList);
    }
}
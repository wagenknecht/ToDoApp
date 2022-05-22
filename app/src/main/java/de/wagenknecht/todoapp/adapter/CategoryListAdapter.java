package de.wagenknecht.todoapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.wagenknecht.todoapp.AppDatabase;
import de.wagenknecht.todoapp.R;
import de.wagenknecht.todoapp.entity.Category;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.myViewHolder>{

    private Context context;
    private List<Category> categoryList;

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    public CategoryListAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_category, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.categoryName.setText(categoryList.get(position).category_name);
        holder.deleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category = categoryList.get(position);
                AppDatabase database = AppDatabase.getDatabase(context);
                if(database.todoDao().getAllPriorityIdFromTodo().contains(category.category_id)) {
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setTitle("Hinweis");
                    alertDialog.setMessage("Es existieren noch ToDos mit dieser Kategorie, daher kann diese nicht gelöscht werden");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                } else {
                    database.categoryDao().removeCategory(category.category_id);
                    categoryList.remove(category);
                    notifyItemRemoved(position);
                }

            }
        });
        holder.saveEditCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category = categoryList.get(position);
                category.category_name = holder.categoryName.getText().toString();
                AppDatabase database = AppDatabase.getDatabase(context);
                database.categoryDao().insertCategory(category);


            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

//    public void remove(int position) {
//        items.remove(position);
//        notifyItemRemoved(position);
//    }

    class myViewHolder extends RecyclerView.ViewHolder {

        EditText categoryName;
        ImageButton deleteCategory;
        ImageButton saveEditCategory;

        public myViewHolder(View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.categoryName);
            deleteCategory = itemView.findViewById(R.id.deleteCategory);
            saveEditCategory = itemView.findViewById(R.id.saveEditCategory);
        }
    }
}
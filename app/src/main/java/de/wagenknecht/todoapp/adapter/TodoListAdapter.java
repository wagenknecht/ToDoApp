package de.wagenknecht.todoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import de.wagenknecht.todoapp.AppDatabase;
import de.wagenknecht.todoapp.R;
import de.wagenknecht.todoapp.entity.Priority;
import de.wagenknecht.todoapp.entity.Todo;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.myViewHolder>{

    private Context context;
    private List<Todo> todoList;
    private onTodoListener monTodoListener;
    private AppDatabase database;

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public TodoListAdapter(Context context, onTodoListener onTodoListener){
        this.context = context;
        this.monTodoListener = onTodoListener;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_todo, parent, false);
        return new myViewHolder(view, monTodoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        database = AppDatabase.getDatabase(context);
        Priority priority = database.priorityDao().getPriority(todo.priority_id);

        holder.itemTitel.setText(todo.title);
        holder.itemBeschreibung.setText(todo.description);
        holder.itemDatum.setText(todo.datetime);
        if(priority != null){
            holder.itemPrioritaet.setText(priority.priority_name);
        }
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView itemTitel;
        TextView itemBeschreibung;
        TextView itemPrioritaet;
        TextView itemDatum;
        onTodoListener onTodoListener;

        public myViewHolder(View itemView, onTodoListener onTodoListener) {
            super(itemView);

            itemTitel = itemView.findViewById(R.id.itemTitel);
            itemBeschreibung = itemView.findViewById(R.id.itemBeschreibung);
            itemPrioritaet = itemView.findViewById(R.id.itemPriorität);
            itemDatum = itemView.findViewById(R.id.itemDatum);
            this.onTodoListener = onTodoListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTodoListener.onTodoClick(getAbsoluteAdapterPosition());
        }
    }

    public interface onTodoListener{
        void onTodoClick(int position);
    }
}





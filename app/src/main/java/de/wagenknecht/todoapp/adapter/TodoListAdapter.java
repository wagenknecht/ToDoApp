package de.wagenknecht.todoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.wagenknecht.todoapp.R;
import de.wagenknecht.todoapp.entity.Todo;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.myViewHolder>{

    private Context context;
    private List<Todo> todoList;
    private onTodoListener monTodoListener;

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
        holder.itemTitel.setText(todoList.get(position).title);
        holder.itemBeschreibung.setText(todoList.get(position).description);
        //holder.itemPriorität.setText(todoList.get(position).priority);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

//    public void remove(int position) {
//        items.remove(position);
//        notifyItemRemoved(position);
//    }

    class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView itemTitel;
        TextView itemBeschreibung;
        TextView itemPrioritaet;
        onTodoListener onTodoListener;

        public myViewHolder(View itemView, onTodoListener onTodoListener) {
            super(itemView);

            itemTitel = itemView.findViewById(R.id.itemTitel);
            itemBeschreibung = itemView.findViewById(R.id.itemBeschreibung);
            itemPrioritaet = itemView.findViewById(R.id.itemPriorität);
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





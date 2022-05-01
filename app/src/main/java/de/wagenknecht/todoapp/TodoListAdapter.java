package de.wagenknecht.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Code aus RecycleBsp genutzt
public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.myViewHolder>{

//    List<String> items;
    private Context context;
    private List<Todo> todoList;

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public TodoListAdapter(Context context){
        this.context = context;
    }

//    public TodoListAdapter(List<String> items) {
//        this.items = items;
//    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item, parent, false);
        return new myViewHolder(view);
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

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView itemTitel;
        TextView itemBeschreibung;
        TextView itemPrioritaet;
        private TodoListAdapter todoListAdapter;

        public myViewHolder(View itemView) {
            super(itemView);

            itemTitel = itemView.findViewById(R.id.itemTitel);
            itemBeschreibung = itemView.findViewById(R.id.itemBeschreibung);
            itemPrioritaet = itemView.findViewById(R.id.itemPriorität);
//            itemView.findViewById(R.id.delete).setOnClickListener(view -> {
//                todoListAdapter.items.remove(getAdapterPosition());
//                todoListAdapter.notifyItemRemoved(getAdapterPosition());
//            });
        }

//        public myViewHolder linkAdapter(TodoListAdapter todoListAdapter){
//            this.todoListAdapter = todoListAdapter;
//            return this;
//        }
    }
}




